package com.example.rtireviews.data

data class Role(
    val id: Long,
    val name: String,
    val userList: List<User>
)
