package com.example.mysmarthome.ui.splashscreen

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.example.mysmarthome.base.BaseSchedulerProvider
import com.example.mysmarthome.data.local.datastore.UserPreferences
import com.example.mysmarthome.model.*
import com.example.mysmarthome.repository.DeviceRepository
import com.example.mysmarthome.repository.RemoteDataRepository
import com.example.mysmarthome.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.*
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@HiltViewModel
class SplashScreenVM @Inject constructor(
    private var userPreferences: UserPreferences,
    private var userRepository: UserRepository,
    private var deviceRepository: DeviceRepository,
    private var remoteDataRepository: RemoteDataRepository,
    private var scheduler : BaseSchedulerProvider,
    mainDispatcher : CoroutineDispatcher
) : ViewModel() {
    val userFirstConnection = userPreferences.firstConnection.asLiveData()
    val currentPrefTheme = userPreferences.currentTheme.asLiveData()
    private val compositeDisposable = CompositeDisposable()
    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(mainDispatcher + job)

    fun setAppTheme(){
        uiScope.launch {
            userPreferences.setAppTheme()
        }
    }

    fun changeConnectionValue(value: Boolean) {
        uiScope.launch {
            userPreferences.changeConnectionValue(value)
        }
    }

    fun retrieveDataFromRemote() : LiveData<Result<ApiResponse>> {
        val result = MutableLiveData<Result<ApiResponse>>()
        compositeDisposable.add(
            remoteDataRepository.getDataFromRemote()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe ({ response ->
                    result.value = Result.success(response)
                }, {
                    result.value = Result.failure(it)
                }))
        return result
    }

    fun insertDataIntoLocalDb(response : ApiResponse){
        insertUserIntoLocalDb(response.user)
        insertDeviceListIntoLocalDb(response.devices)
    }

    private fun insertUserIntoLocalDb(user: User) {
        uiScope.launch {userRepository.insertUser(user) }
    }

    private fun insertDeviceListIntoLocalDb(devices: List<Device>) {
        uiScope.launch { deviceRepository.insertDeviceList(devices) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        job.cancel()
    }
}