package com.example.mysmarthome.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mysmarthome.DummyData
import com.example.mysmarthome.data.local.roomdatabase.DeviceDao
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeviceRepositoryTest {
    private val deviceDao = mockk<DeviceDao>(relaxed = true)
    private lateinit var deviceRepo: DeviceRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        deviceRepo = DeviceRepository(deviceDao)
    }

    @Test
    fun testGetDeviceListFromLocal() {
        coEvery { deviceDao.getDeviceList() } returns MutableLiveData(listOf(DummyData.lightEntity))
        deviceRepo.getDeviceList().observeForever {
            assert(it[0] == DummyData.lightEntity.toDomainObj())
        }
    }

    @Test
    fun testGetFilteredList() {
        val deviceList = listOf(DummyData.lightEntity, DummyData.rollerShutterEntity)
        coEvery { deviceDao.getFilteredList(mockk()) } returns MutableLiveData(
            listOf(DummyData.rollerShutterEntity))
        deviceRepo.getDeviceList().observeForever {
            assert(it.size == 1)
            assert(it[0] == deviceList[1].toDomainObj())
        }
    }
}