package com.example.mysmarthome.repository

import com.example.mysmarthome.data.remote.ApiService
import com.example.mysmarthome.model.ApiResponse
import io.reactivex.rxjava3.core.Flowable

class DeviceRepository(val apiService: ApiService) : IDeviceRepository {
    override fun getDataFromRemote(): Flowable<ApiResponse> = apiService.queryData()
}