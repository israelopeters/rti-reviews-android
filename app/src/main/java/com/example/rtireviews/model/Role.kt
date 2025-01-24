package com.example.rtireviews.model

data class Role(
    val id: Long,
    val name: String,
    val userList: List<User>
)
