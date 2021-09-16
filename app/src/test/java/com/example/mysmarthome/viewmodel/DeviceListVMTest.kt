package com.example.mysmarthome.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mysmarthome.DummyData
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.model.ProductType
import com.example.mysmarthome.repository.DeviceRepository
import com.example.mysmarthome.ui.devicelist.DeviceListVM
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DeviceListVMTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val deviceRepo = mockk<DeviceRepository>(relaxed = true)
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: DeviceListVM

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = DeviceListVM(deviceRepo, dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetDevices() {
        val devicesList = listOf(DummyData.light, DummyData.rollerShutter)
        coEvery { deviceRepo.getDeviceList() } returns MutableLiveData(devicesList)

        viewModel.devices.observeForever {
            assert(viewModel.devices.value == devicesList)
        }
    }
/*
    @Test
    fun testGetFilteredList() {
        val query = listOf(ProductType.LIGHT)
        coEvery { deviceRepo.getFilteredList(query) } returns MutableLiveData(listOf(mockk<Light>()))

        viewModel.getFilteredList(query).observeForever {
            assert(viewModel.getFilteredList(query).value == listOf<Light>())
        }
    }*/
}