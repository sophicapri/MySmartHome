package com.example.mysmarthome.ui.devicelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.ProductType
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceListVM @Inject constructor(private var deviceRepository: DeviceRepository): ViewModel() {

    fun getDevices(): LiveData<List<Device>> = deviceRepository.getDeviceListFromLocal()

    fun getFilteredList(productTypes: List<ProductType>): LiveData<List<Device>> {
        val query = SimpleSQLiteQuery(makeQuery(productTypes))
        return deviceRepository.getFilteredList(query)
    }

    private fun makeQuery(productTypes: List<ProductType>): String {
        var stringQuery = "SELECT * FROM deviceentity WHERE "
        var emptyQuery = true

        productTypes.forEach {
            if (emptyQuery) {
                stringQuery += "productType LIKE '%${it.name}%'"
                emptyQuery = false
            } else
                stringQuery += "OR productType LIKE '%${it.name}%'"
        }
        return stringQuery
    }
}