package com.example.mysmarthome.ui.userprofile

import androidx.lifecycle.ViewModel
import com.example.mysmarthome.data.local.datastore.UserPreferences
import com.example.mysmarthome.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileVM @Inject constructor(
    private var userRepository: UserRepository,
    private var userPreferences: UserPreferences
) : ViewModel(){

    fun getUser() = userRepository.getUser()
}