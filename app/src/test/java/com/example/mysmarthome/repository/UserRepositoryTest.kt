package com.example.mysmarthome.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mysmarthome.data.local.roomdatabase.DeviceDao
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.ProductType
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserRepositoryTest{

    private val deviceDao = mockk<DeviceDao>(relaxed = true)
    private lateinit var deviceRepo: DeviceRepository
    val deviceLight = DeviceEntity(1, "", DeviceMode.OFF,
        0, null, null, ProductType.LIGHT)
    val deviceRollerShutter = DeviceEntity(2, "", DeviceMode.ON,
        0, null, 0, ProductType.ROLLER_SHUTTER)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        deviceRepo = DeviceRepository(deviceDao)
    }

    @Test
    fun testInsertUser() {
        coEvery { deviceDao.getDeviceList() } returns MutableLiveData(listOf(deviceLight))
        deviceRepo.getDeviceList().observeForever {
            assert(it[0] == deviceLight.toDomainObj())
        }
    }

    @Test
    fun testGetUser() {

    }

    @Test
    fun testUpdateUser() {}
}