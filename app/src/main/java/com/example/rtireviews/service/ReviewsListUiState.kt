package com.example.rtireviews.service

import com.example.rtireviews.model.Review
import com.example.rtireviews.data.TestData

data class ReviewsListUiState(
    val currentReviewsList: List<Review> = TestData.generateReviewsData()
)
