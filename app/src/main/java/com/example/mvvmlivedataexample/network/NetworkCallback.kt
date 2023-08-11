package com.example.mvvmlivedataexample.network


interface NetworkCallback<T> {
    fun onSuccess(data: List<T>?)
    fun onError(error: String?)
}