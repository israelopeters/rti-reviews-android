package com.example.rtireviews.data

class Review(
    val id: Long,
    val title: String,
    val body: String,
    val image: Int,
    val author: String,
    val timePosted: String,
    val comments: List<Comment>
)
