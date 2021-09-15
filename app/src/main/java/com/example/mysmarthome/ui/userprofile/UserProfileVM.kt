package com.example.mysmarthome.ui.userprofile

import androidx.lifecycle.*
import com.example.mysmarthome.datastore.UserPreferences
import com.example.mysmarthome.model.User
import com.example.mysmarthome.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileVM @Inject constructor(
    private var userRepository: UserRepository,
    private var userPreferences: UserPreferences,
    mainDispatcher : CoroutineDispatcher
) : ViewModel() {
    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(mainDispatcher + job)

    val user = userRepository.getUser()

    fun updateUser(user: User) {
        uiScope.launch {
            userRepository.updateUser(user)
        }
    }

    fun updateUserPrefNightMode(){
        uiScope.launch {
            userPreferences.toggleNightMode()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}