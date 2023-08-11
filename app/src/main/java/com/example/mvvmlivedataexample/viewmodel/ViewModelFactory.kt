package com.example.mvvmlivedataexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlivedataexample.model.CarRepository


class ViewModelFactory(private val repository: CarRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarViewModel(repository) as T
    }
}