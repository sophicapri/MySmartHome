package com.example.mysmarthome.ui.device.heater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.model.Heater
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeaterDetailVM @Inject constructor(private var deviceRepository: DeviceRepository) : ViewModel() {

    fun getHeaterById(id: Int) = deviceRepository.getDeviceById(id).map { it as Heater }

    fun updateHeater(heater: Heater){
        viewModelScope.launch {
            deviceRepository.updateDevice(heater)
        }
    }
}