package com.example.rtireviews.model

class Comment(
    val id: Long,
    val author: String,
    val body: String,
    val timePosted: String, //TODO: Refactor to LocalDateTime
    val likes: Long
)
