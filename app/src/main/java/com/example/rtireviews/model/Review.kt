package com.example.rtireviews.model

import java.time.LocalDateTime

data class Review(
    val id: Long,
    var title: String,
    var body: String,
    val image: Int,
    val likeCount: Long,
    val bookGenre: String,
    val timePosted: LocalDateTime,
    var comments: List<Comment> = listOf(),
    val genreList: List<ReviewGenre> = listOf(),
    val author: User,
)

enum class ReviewGenre {
    THRILLER,
    CRIME,
    SELF_HELP,
    ROMANCE,
    FICTION,
    AFRICAN,
    BIOGRAPHY,
    HISTORY
}
