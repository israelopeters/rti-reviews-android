package com.example.rtireviews.data

class Review(
    val id: Long,
    var title: String,
    var body: String,
    val image: Int,
    val author: String,
    val timePosted: String,
    var comments: List<Comment> = listOf()
)
