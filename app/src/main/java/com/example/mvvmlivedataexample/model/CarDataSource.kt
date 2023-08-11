package com.example.mvvmlivedataexample.model

import com.example.mvvmlivedataexample.network.NetworkCallback



interface CarDataSource {
     fun retrieveCars(callback: NetworkCallback<Car>)
}