package com.example.mysmarthome.ui.splashscreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mysmarthome.datastore.UserPreferences
import com.example.mysmarthome.repository.DeviceRepository
import com.example.mysmarthome.repository.RemoteDataRepository
import com.example.mysmarthome.repository.UserRepository
import com.example.mysmarthome.util.TestSchedulerProvider
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Rule
import org.junit.Before
import org.junit.Test


class SplashScreenVMTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var userPrefs: UserPreferences

    @MockK
    lateinit var userRepo: UserRepository

    @MockK
    lateinit var deviceRepo: DeviceRepository

    @MockK
    lateinit var remoteDataRepo: RemoteDataRepository
    private val schedulerProvider = TestSchedulerProvider()
    private val dispatcher = Dispatchers.Unconfined
    private lateinit var viewModel: SplashScreenVM

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
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
    }

    @Test
    fun testGetUserFirstConnection() {

    }

    @Test
    fun testGetCurrentTheme() {
    }

    @Test
    fun testGetDataRetrieved() {
    }

    @Test
    fun testChangeConnectionValue() {
    }

    @Test
    fun testSetAppTheme() {
    }

    @Test
    fun testLoadDataFromRemote() {
    }
}