package com.example.mysmarthome.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mysmarthome.data.local.roomdatabase.DeviceDao
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.model.ProductType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeviceRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val deviceDao = mockk<DeviceDao>(relaxed = true)
    private lateinit var deviceRepo: DeviceRepository

    @Before
    fun setUp() {
        deviceRepo = DeviceRepository(deviceDao)
    }

    @Test
    fun testInsertLights(): Unit = runBlocking {
        coEvery {

        }
    }

    @Test
    fun testInsertHeaters() {
    }

    @Test
    fun testInsertRollerShutters() {
    }

    @Test
    fun testGetDeviceListFromLocal() {
        val deviceMock = DeviceEntity(
            1, "", DeviceMode.OFF,
            0, null, null, ProductType.LIGHT)

        coEvery { deviceDao.getDeviceList() } returns MutableLiveData(listOf(deviceMock))

        deviceRepo.getDeviceList().observeForever {
            assert(it[0] == deviceMock.toDomainObj())
        }
    }

    @Test
    fun testGetFilteredList() {
    }

    @Test
    fun testUpdateDevice() {
    }

    @Test
    fun testDeleteDevices() {
    }
}