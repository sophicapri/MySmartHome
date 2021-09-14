package com.example.mysmarthome.ui.splashscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.example.mysmarthome.data.local.datastore.UserPreferences
import com.example.mysmarthome.helper.SchedulerProvider
import com.example.mysmarthome.model.*
import com.example.mysmarthome.repository.DeviceRepository
import com.example.mysmarthome.repository.RemoteDataRepository
import com.example.mysmarthome.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@HiltViewModel
class SplashScreenVM @Inject constructor(
    private var userPreferences: UserPreferences,
    private var userRepository: UserRepository,
    private var deviceRepository: DeviceRepository,
    private var remoteDataRepository: RemoteDataRepository,
    private var scheduler : SchedulerProvider
) : ViewModel() {
    val userFirstConnection = userPreferences.firstConnection.asLiveData()
    val dataRetrieved: LiveData<Boolean>
        get() = _dataRetrieved
    private var _dataRetrieved = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()
    val currentTheme = userPreferences.currentTheme.asLiveData()

    fun changeConnectionValue(value: Boolean) {
        viewModelScope.launch {
            userPreferences.changeConnectionValue(value)
        }
    }

    fun setAppTheme(){
        viewModelScope.launch {
            userPreferences.setAppTheme()
        }
    }

    fun loadDataFromRemote() {
        compositeDisposable.add(
            remoteDataRepository.getDataFromRemote()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({ response ->
                    _dataRetrieved.postValue(true)
                    insertUserIntoLocalDb(response.user)
                    insertDevicesToLocalDb(response.devices)
                }, {
                    Log.e("MainViewModel", "getDataFromRemote: Error = ${it.stackTraceToString()}")
                })
        )
    }

    private fun insertUserIntoLocalDb(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    private fun insertDevicesToLocalDb(devices: List<Device>) {
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}