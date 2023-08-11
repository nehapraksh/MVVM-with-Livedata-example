package com.example.mvvmlivedataexample.network

import com.example.mvvmlivedataexample.model.Car
import com.example.mvvmlivedataexample.model.CarDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CarNetworkRequestQueue(apiClient: ApiClient) : CarDataSource {

    private var call: Call<List<Car>>? = null
    private val service = apiClient.build()

    override fun retrieveCars(callback: NetworkCallback<Car>) {
        call = service.getCars()
        call?.enqueue(object : Callback<List<Car>> {
            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                //callback.onError(t.message)
                callback.onError("Oops! Some error occurred.")
            }

            override fun onResponse(
                call: Call<List<Car>>,
                response: Response<List<Car>>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    } else {
                        callback.onError("Oops! Some error occurred.")
                    }
                }
            }
        })
    }
}