package com.example.rtireviews.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rtireviews.data.ApiRepository
import com.example.rtireviews.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val apiRepository: ApiRepository
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

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            safelyCall { authenticatedUser = apiRepository.getUser(listOf(email, password)) }
        }
    }

    suspend fun <T> safelyCall(execute: suspend () -> T): Result<T> = try {
        Result.success(execute())
    } catch (e: Exception) {
        Result.failure(Throwable(message = e.message ?: "Network request error!"))
    }

}