package com.example.mysmarthome.ui.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.data.local.datastore.UserPreferences
import com.example.mysmarthome.model.User
import com.example.mysmarthome.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileVM @Inject constructor(
    private var userRepository: UserRepository,
    private var userPreferences: UserPreferences
) : ViewModel(){

    val user = userRepository.getUser()

    fun updateUser(user: User){
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }
}