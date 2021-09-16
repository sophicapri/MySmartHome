package com.example.mysmarthome.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mysmarthome.data.remote.ApiService
import com.example.mysmarthome.model.ApiResponse
import io.mockk.coEvery
import io.mockk.mockk
import io.reactivex.rxjava3.core.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RemoteDataRepositoryTest {

    private val apiService = mockk<ApiService>(relaxed = true)
    private lateinit var remoteDataRepo: RemoteDataRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        remoteDataRepo = RemoteDataRepository(apiService)
    }


    @Test
    fun testGetDataFromRemote() {
        val response = ApiResponse(listOf(mockk()), mockk())

        coEvery { apiService.queryData() } returns Flowable.just(response)

        remoteDataRepo.getDataFromRemote().test().assertValue(response)
    }
}