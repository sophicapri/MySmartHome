package com.example.mysmarthome.data.remote

import com.example.mysmarthome.model.ApiResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET("modulotest/data.json")
    fun queryData(): Flowable<ApiResponse>

    companion object{
        const val API_URL = "http://storage42.com"
    }
}