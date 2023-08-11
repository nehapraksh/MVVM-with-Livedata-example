package com.example.mvvmlivedataexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmlivedataexample.model.Car
import com.example.mvvmlivedataexample.model.CarRepository
import com.example.mvvmlivedataexample.network.NetworkCallback

class CarViewModel(private val repository: CarRepository) : ViewModel() {
    private val _cars = MutableLiveData<List<Car>>().apply { value = emptyList() }
    private val _isViewLoading = MutableLiveData<Boolean>()
    private val _onMessageError = MutableLiveData<Any>()
    private val _isEmptyList = MutableLiveData<Boolean>()
    val cars: LiveData<List<Car>>
    val isViewLoading: LiveData<Boolean>
    val onMessageError: LiveData<Any>
    val isEmptyList: LiveData<Boolean>

    init {
        cars = _cars
        isViewLoading = _isViewLoading
        onMessageError = _onMessageError
        isEmptyList = _isEmptyList
        loadCars()
    }

    fun loadCars() {
        _isViewLoading.value = true
        repository.fetchCars(object : NetworkCallback<Car> {
            override fun onError(error: String?) {
                _isViewLoading.value = false
                _onMessageError.value = error
            }

            override fun onSuccess(data: List<Car>?) {
                _isViewLoading.value = false
                if (data.isNullOrEmpty()) {
                    _isEmptyList.value = true

                } else {
                    _cars.value = data
                }
            }
        })
    }
}