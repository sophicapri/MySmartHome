package com.example.mysmarthome.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mysmarthome.datastore.UserPreferences
import com.example.mysmarthome.model.ApiResponse
import com.example.mysmarthome.repository.DeviceRepository
import com.example.mysmarthome.repository.RemoteDataRepository
import com.example.mysmarthome.repository.UserRepository
import com.example.mysmarthome.ui.splashscreen.SplashScreenVM
import com.example.mysmarthome.util.TestSchedulerProvider
import io.mockk.coEvery
import io.mockk.mockk
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SplashScreenVMTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val userPrefs = mockk<UserPreferences>(relaxed = true)
    private val userRepo = mockk<UserRepository>(relaxed = true)
    private val deviceRepo = mockk<DeviceRepository>(relaxed = true)
    private val remoteDataRepo = mockk<RemoteDataRepository>(relaxed = true)
    private val dispatcher = TestCoroutineDispatcher()
    private val schedulerProvider = TestSchedulerProvider()
    private lateinit var viewModel: SplashScreenVM

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = SplashScreenVM(
            userPrefs,
            userRepo,
            deviceRepo,
            remoteDataRepo,
            schedulerProvider,
            dispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetUserFirstConnection() {
        coEvery { userPrefs.firstConnection } returns MutableStateFlow(false)
        viewModel.userFirstConnection.observeForever {
            assert(viewModel.userFirstConnection.value != null)
            assert(viewModel.userFirstConnection.value == false)
        }
    }

    @Test
    fun testLoadDataFromRemote_Success() {
        val apiResponse = ApiResponse(listOf(mockk()), mockk())
        coEvery { remoteDataRepo.getDataFromRemote() } returns Flowable.just(
            apiResponse
        )
        viewModel.loadDataFromRemote().observeForever {
            assert(viewModel.loadDataFromRemote().value != null)
            assert(viewModel.loadDataFromRemote().value?.getOrNull() == apiResponse)
        }
    }
}