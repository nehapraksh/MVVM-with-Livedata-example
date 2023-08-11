package com.example.mvvmlivedataexample.di

import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlivedataexample.model.CarDataSource
import com.example.mvvmlivedataexample.model.CarRepository
import com.example.mvvmlivedataexample.network.ApiClient
import com.example.mvvmlivedataexample.network.CarNetworkRequestQueue
import com.example.mvvmlivedataexample.viewmodel.ViewModelFactory


object Injection {

    private val carDataSource: CarDataSource = CarNetworkRequestQueue(ApiClient)
    private val carRepository = CarRepository(carDataSource)
    private val carViewModelFactory = ViewModelFactory(carRepository)

    fun providerRepository(): CarDataSource {
        return carDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return carViewModelFactory
    }
}