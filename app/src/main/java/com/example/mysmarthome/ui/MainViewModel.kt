package com.example.mysmarthome.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.mysmarthome.data.local.datastore.UserPreferences
import com.example.mysmarthome.model.*
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject


// TODO: change compositeDisposable management
@HiltViewModel
class MainViewModel @Inject constructor(
    private var deviceRepository: DeviceRepository,
    private var userPreferences: UserPreferences
) : ViewModel() {
    private val remoteDevices = MutableLiveData<List<Device>>()
    private val user = MutableLiveData<User>()
    private val compositeDisposable = CompositeDisposable()
    val userFirstConnection = userPreferences.firstConnection.asLiveData()

    init {
        getDataFromRemote()
    }

    private fun getDataFromRemote() {
        compositeDisposable.add(
            deviceRepository.getDataFromRemote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.d("MainViewModel", "getDataFromRemote: list = ${response.devices.filterIsInstance<Heater>()}")
                    remoteDevices.postValue(response.devices.filterIsInstance<Light>())
                    user.postValue(response.user)
                    insertDevices(response.devices)
                }, {
                    Log.e("MainViewModel", "getDataFromRemote: Error = ${it.stackTraceToString()}")
                })
        )
    }

    fun getUser(): LiveData<User> = user

    private fun insertDevices(devices: List<Device>){
        val lights = devices.filterIsInstance<Light>()
        val heaters = devices.filterIsInstance<Heater>()
        val rollerShutters = devices.filterIsInstance<RollerShutter>()
        insertLights(lights)
        insertHeaters(heaters)
        insertRollerShutters(rollerShutters)

    }

    private fun insertLights(lights: List<Light>) {
        viewModelScope.launch {
            deviceRepository.insertLights(lights)
        }
    }

    private fun insertHeaters(heaters: List<Heater>) {
        viewModelScope.launch {
            deviceRepository.insertHeaters(heaters)
        }
    }

    private fun insertRollerShutters(rollerShutters: List<RollerShutter>) {
        viewModelScope.launch {
            deviceRepository.insertRollerShutters(rollerShutters)
        }
    }

    fun getDevicesFromLocal() : LiveData<List<Device>> {
        val deviceMediatorLiveData = MediatorLiveData<List<Device>>()
        deviceMediatorLiveData.addSource(deviceRepository.getLightListFromLocal()) { lightEntityList ->
            if (lightEntityList != null) {
                deviceMediatorLiveData.value = lightEntityList.map { it.toLight() }
            }
        }
        deviceMediatorLiveData.addSource(deviceRepository.getHeaterListFromLocal()) { heaterEntityList ->
            if (heaterEntityList != null) {
                deviceMediatorLiveData.value = heaterEntityList.map { it.toHeater() }
            }
        }
        deviceMediatorLiveData.addSource(deviceRepository.getRollerShutterListFromLocal()) { rollerShutterEntityList ->
            if (rollerShutterEntityList != null) {
                deviceMediatorLiveData.value = rollerShutterEntityList.map { it.toRollerShutter() }
            }
        }
        return deviceMediatorLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun changeConnectionValue(value: Boolean) {
        viewModelScope.launch {
            userPreferences.changeConnectionValue(value)
        }
    }
}