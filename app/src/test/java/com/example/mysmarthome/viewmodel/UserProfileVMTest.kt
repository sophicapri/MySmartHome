package com.example.mysmarthome.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mysmarthome.data.local.datastore.UserPreferences
import com.example.mysmarthome.model.User
import com.example.mysmarthome.repository.UserRepository
import com.example.mysmarthome.ui.userprofile.UserProfileVM
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
class UserProfileVMTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val userPrefs = mockk<UserPreferences>(relaxed = true)
    private val userRepo = mockk<UserRepository>(relaxed = true)
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: UserProfileVM

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = UserProfileVM(userRepo, userPrefs, dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetUser_Successful() {
        coEvery { userRepo.getUser() } returns MutableLiveData(mockk())

        viewModel.user.observeForever {
            assert(viewModel.user.value != null)
            assert(viewModel.user.value is User)
        }
    }
}