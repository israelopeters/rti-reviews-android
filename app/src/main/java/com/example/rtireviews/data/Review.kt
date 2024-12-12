package com.example.rtireviews.data

data class Review(
    val title: String,
    val body: String,
    val image: Int,
    val author: String,
    val timePosted: String,
    val comments: List<Comment>
)
