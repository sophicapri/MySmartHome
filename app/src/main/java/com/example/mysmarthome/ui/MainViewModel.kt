package com.example.mysmarthome.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.model.User
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


// TODO: change compositeDisposable management
@HiltViewModel
class MainViewModel @Inject constructor(var deviceRepository: DeviceRepository) : ViewModel() {
    private val devices = MutableLiveData<List<Device>>()
    private val user = MutableLiveData<User>()
    private val compositeDisposable = CompositeDisposable()

    init {
        getDataFromRemote()
    }

    private fun getDataFromRemote() {
        compositeDisposable.add(
            deviceRepository.getDataFromRemote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.d("MainViewModel", "getDataFromRemote: list = ${response.devices}")
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
}