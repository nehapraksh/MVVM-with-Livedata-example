package com.example.mvvmlivedataexample.model

import com.example.mvvmlivedataexample.network.NetworkCallback

class CarRepository(private val carDataSource: CarDataSource) {

    fun fetchCars(callback: NetworkCallback<Car>) {
        carDataSource.retrieveCars(callback)
    }
}