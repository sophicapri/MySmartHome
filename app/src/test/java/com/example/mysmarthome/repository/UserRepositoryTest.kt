package com.example.mysmarthome.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mysmarthome.data.local.roomdatabase.DeviceDao
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.UserDao
import com.example.mysmarthome.datastore.UserPreferences
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.ProductType
import com.example.mysmarthome.model.User
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserRepositoryTest {

    private val userDao = mockk<UserDao>(relaxed = true)
    private lateinit var userRepository: UserRepository
    private val user = mockk<User>()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        userRepository = UserRepository(userDao)
    }

    @Test
    fun testGetUser() {
        coEvery { userDao.getUser() } returns MutableLiveData(user)
        userRepository.getUser().observeForever {
            assert(userRepository.getUser().value == user)
        }
    }

    @Test
    fun testUpdateUser() = runBlocking{
        val rowId = 1
        coEvery { userDao.updateUser(user) } returns rowId
        val rowIdReceived = userRepository.updateUser(user)
        assert(rowIdReceived == rowId)
    }
}