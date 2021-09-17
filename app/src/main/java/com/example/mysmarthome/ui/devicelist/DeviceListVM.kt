package com.example.mysmarthome.ui.devicelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        val query = ProductType.queryBuilder(productTypes)
        return deviceRepository.getFilteredDeviceList(query)
    }

    fun deleteDevices(device: List<Device>) {
        uiScope.launch { deviceRepository.deleteDeviceList(device) }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun insertDevice(device: Device): LiveData<Long> {
        val rowId = MutableLiveData<Long>()
        uiScope.launch { rowId.value = deviceRepository.insertDevice(device) }
        return rowId
    }
}