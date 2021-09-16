package com.example.mysmarthome.ui.device.rollershutter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.model.RollerShutter
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RollerShutterDetailVM @Inject constructor(
    private var deviceRepository: DeviceRepository,
    mainDispatcher: CoroutineDispatcher
) :
    ViewModel() {
    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(mainDispatcher + job)


    fun updateRollerShutter(rollerShutter: RollerShutter) {
        uiScope.launch { deviceRepository.updateDevice(rollerShutter) }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}