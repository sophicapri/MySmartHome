package com.example.mysmarthome.ui.device.light

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LightDetailVM @Inject constructor(
    private var deviceRepository: DeviceRepository,
    mainDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(mainDispatcher + job)

    fun updateLight(light: Light) {
        uiScope.launch {
            deviceRepository.updateDevice(light)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    // fun getLightById(id: Int) = deviceRepository.getDeviceById(id).map { it as Light }
}