package com.example.mysmarthome.ui.device.light

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LightDetailVM @Inject constructor(private var deviceRepository: DeviceRepository) : ViewModel() {

    fun updateLight(light: Light){
        viewModelScope.launch {
            deviceRepository.updateDevice(light)
        }
    }

    // fun getLightById(id: Int) = deviceRepository.getDeviceById(id).map { it as Light }
}