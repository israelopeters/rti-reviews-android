package com.example.rtireviews.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.rtireviews.data.ApiRepository
import com.example.rtireviews.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private var apiRepository: ApiRepository
): ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var authenticatedUser by mutableStateOf(User("", "", "", listOf()))

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    suspend fun getUser() {
        // Add exception handling
        authenticatedUser = apiRepository.getUser(email)
    }

}