package com.example.mvvmlivedataexample

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mvvmlivedataexample.model.*
import com.example.mvvmlivedataexample.network.NetworkCallback
import com.example.mvvmlivedataexample.viewmodel.CarViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.*
import org.mockito.Mockito.*


class CarViewModelUnitTest {

    @Mock
    private lateinit var carDataSource: CarDataSource

    @Mock
    private lateinit var context: Application

    @Captor
    private lateinit var networkCallbackCaptor: ArgumentCaptor<NetworkCallback<Car>>

    private lateinit var viewModel: CarViewModel
    private lateinit var repository: CarRepository

    private lateinit var isViewLoadingObserver: Observer<Boolean>
    private lateinit var onMessageErrorObserver: Observer<Any>
    private lateinit var emptyListObserver: Observer<Boolean>
    private lateinit var onRenderCarsObserver: Observer<List<Car>>

    private lateinit var carEmptyList: List<Car>
    private lateinit var carList: List<Car>

    /**
    //https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
    //Method getMainLooper in android.os.Looper not mocked

    java.lang.IllegalStateException: networkCallbackCaptor.capture() must not be null
     */
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        `when`(context.applicationContext).thenReturn(context)

        repository = CarRepository(carDataSource)
        viewModel = CarViewModel(repository)

        mockData()
        setupObservers()
    }

    @Test
    fun `retrieve cars with ViewModel and Repository returns empty data`() {
        with(viewModel) {
            //loadCars()
            isViewLoading.observeForever(isViewLoadingObserver)
            isEmptyList.observeForever(emptyListObserver)
            cars.observeForever(onRenderCarsObserver)
        }

        verify(carDataSource, times(1)).retrieveCars(capture(networkCallbackCaptor))
        networkCallbackCaptor.value.onSuccess(carEmptyList)

        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertTrue(viewModel.isEmptyList.value == true)
        Assert.assertTrue(viewModel.cars.value?.size == 0)
    }

    @Test
    fun `retrieve cars with ViewModel and Repository returns full data`() {
        with(viewModel) {
            //loadCars()
            isViewLoading.observeForever(isViewLoadingObserver)
            cars.observeForever(onRenderCarsObserver)
        }

        verify(carDataSource, times(1)).retrieveCars(capture(networkCallbackCaptor))
        networkCallbackCaptor.value.onSuccess(carList)

        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertTrue(viewModel.cars.value?.size == 3)
    }

    @Test
    fun `retrieve cars with ViewModel and Repository returns an error`() {
        with(viewModel) {
            //loadCars()
            isViewLoading.observeForever(isViewLoadingObserver)
            onMessageError.observeForever(onMessageErrorObserver)
        }
        verify(carDataSource, times(1)).retrieveCars(capture(networkCallbackCaptor))
        networkCallbackCaptor.value.onError("An error occurred")
        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertNotNull(viewModel.onMessageError.value)
    }

    private fun setupObservers() {
        isViewLoadingObserver = mock(Observer::class.java) as Observer<Boolean>
        onMessageErrorObserver = mock(Observer::class.java) as Observer<Any>
        emptyListObserver = mock(Observer::class.java) as Observer<Boolean>
        onRenderCarsObserver = mock(Observer::class.java) as Observer<List<Car>>
    }

    private fun mockData() {
        carEmptyList = emptyList()
        val mockList: MutableList<Car> = mutableListOf()
        mockList.add(
            Car(
                Make("Lexus", "RX 300"),
                "Black",
                2013,
                Configuration("Saloon",6,180),
                "Korean",
                19,
                "",
                "24300"

            )
        )
        mockList.add(Car(
            Make("Audi", "A5"),
            "Blue",
            2020,
            Configuration("Saloon",8,230),
            "Germany",
            17,
            "",
            "41000"

        ))
        mockList.add(Car(
            Make("Lexus", "RX 300"),
            "Black",
            2013,
            Configuration("Saloon",6,180),
            "Korean",
            19,
            "",
            "24300"

        ))

        carList = mockList.toList()
    }
}