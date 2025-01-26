package com.example.rtireviews.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.rtireviews.data.ApiRepository

class UserViewModel(
    private var apiRepository: ApiRepository = ApiRepository()
): ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    init {
        apiRepository = ApiRepository()
    }

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

}