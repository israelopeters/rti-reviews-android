package com.example.rtireviews.model

import java.time.LocalDate

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val roles: List<Role>
)
