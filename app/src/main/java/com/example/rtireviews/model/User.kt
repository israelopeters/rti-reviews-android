package com.example.rtireviews.model

import java.time.LocalDate

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val country: String,
    val bio: String,
    val email: String,
    val password: String,
    val dateCreated: String, // Resolve to LocalDate to match API response
    val reviews: Set<Review>,
    val roles: List<Role>
)
