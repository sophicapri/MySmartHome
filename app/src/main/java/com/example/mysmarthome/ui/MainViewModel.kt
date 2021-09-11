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
    var deviceRepository: DeviceRepository,
    var userPreferences: UserPreferences
) : ViewModel() {
    private val devices = MutableLiveData<List<Device>>()
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
                    devices.postValue(response.devices.filterIsInstance<Light>())
                    user.postValue(response.user)
                }, {
                    Log.e("MainViewModel", "getDataFromRemote: Error = ${it.stackTraceToString()}")
                })
        )
    }

    fun getUser(): LiveData<User> = user

    fun getDeviceLight(): LiveData<List<Device>> = devices

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