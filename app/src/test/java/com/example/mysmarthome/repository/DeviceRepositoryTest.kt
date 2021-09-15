package com.example.mysmarthome.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.mysmarthome.data.local.roomdatabase.DeviceDao
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.ProductType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeviceRepositoryTest {
    private val deviceDao = mockk<DeviceDao>(relaxed = true)
    private lateinit var deviceRepo: DeviceRepository
    private val deviceLight = DeviceEntity(1, "", DeviceMode.OFF,
        0, null, null, ProductType.LIGHT)
    private val deviceRollerShutter = DeviceEntity(2, "", DeviceMode.ON,
        0, null, 0, ProductType.ROLLER_SHUTTER)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        deviceRepo = DeviceRepository(deviceDao)
    }

    @Test
    fun testGetDeviceListFromLocal() {
        coEvery { deviceDao.getDeviceList() } returns MutableLiveData(listOf(deviceLight))
        deviceRepo.getDeviceList().observeForever {
            assert(it[0] == deviceLight.toDomainObj())
        }
    }

    @Test
    fun testGetFilteredList() {
        val deviceList = listOf(deviceLight, deviceRollerShutter)
        coEvery { deviceDao.getFilteredList(mockk()) } returns MutableLiveData(listOf(deviceRollerShutter))
        deviceRepo.getDeviceList().observeForever {
            assert(it.size == 1)
            assert(it[0] == deviceList[1].toDomainObj())
        }
    }

    @Test
    fun testUpdateDevice() {
    }

    @Test
    fun testDeleteDevices() {
    }

    @Test
    fun testInsertLights(): Unit = runBlocking {
        //coEvery {}
    }

    @Test
    fun testInsertHeaters() {}

    @Test
    fun testInsertRollerShutters() {}
}