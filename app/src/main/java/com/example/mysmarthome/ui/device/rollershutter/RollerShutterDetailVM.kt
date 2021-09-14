package com.example.mysmarthome.ui.device.rollershutter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.model.RollerShutter
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RollerShutterDetailVM @Inject constructor(private var deviceRepository: DeviceRepository) : ViewModel() {

    //fun getRollerShutterById(id: Int) = deviceRepository.getDeviceById(id).map { it as RollerShutter }

    fun updateRollerShutter(rollerShutter: RollerShutter){
        viewModelScope.launch {
            deviceRepository.updateDevice(rollerShutter)
        }
    }
}