package com.example.mysmarthome.repository

import com.example.mysmarthome.model.ApiResponse
import io.reactivex.rxjava3.core.Flowable

interface IDeviceRepository {
    fun getDataFromRemote(): Flowable<ApiResponse>
}