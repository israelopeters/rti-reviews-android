package com.example.rtireviews.data

import java.time.LocalDate

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val country: String,
    val bio: String,
    val email: String,
    val password: String,
    val dateCreated: LocalDate,
    val reviews: Set<Review>,
    val roles: List<Role>
)
