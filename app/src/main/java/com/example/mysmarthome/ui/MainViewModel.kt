package com.example.mysmarthome.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.mysmarthome.data.local.datastore.UserPreferences
import com.example.mysmarthome.model.*
import com.example.mysmarthome.repository.DeviceRepository
import com.example.mysmarthome.repository.RemoteDataRepository
import com.example.mysmarthome.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject


// TODO: add Scheduler in constructor for Rx calls
@HiltViewModel
class MainViewModel @Inject constructor(
    private var deviceRepository: DeviceRepository,
) : ViewModel() {



}