package com.example.mysmarthome.ui.device.heater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.model.Heater
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeaterDetailVM @Inject constructor(
    private var deviceRepository: DeviceRepository,
    mainDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(mainDispatcher + job)

    // fun getHeaterById(id: Int) = deviceRepository.getDeviceById(id).map { it as Heater }

    fun updateHeater(heater: Heater) {
        uiScope.launch {
            deviceRepository.updateDevice(heater)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}