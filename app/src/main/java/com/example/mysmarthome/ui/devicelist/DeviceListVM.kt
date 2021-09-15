package com.example.mysmarthome.ui.devicelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.ProductType
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceListVM @Inject constructor(
    private var deviceRepository: DeviceRepository, mainDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(mainDispatcher + job)
    val devices = deviceRepository.getDeviceList()


    fun getFilteredList(productTypes: List<ProductType>): LiveData<List<Device>> {
        val query = SimpleSQLiteQuery(makeQuery(productTypes))
        return deviceRepository.getFilteredList(query)
    }

    private fun makeQuery(productTypes: List<ProductType>): String {
        var stringQuery = BASE_QUERY
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

    fun deleteDevices(device: List<Device>) {
        uiScope.launch { deviceRepository.deleteDevices(device) }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    companion object {
        private const val BASE_QUERY = "SELECT * FROM deviceentity WHERE "
    }
}