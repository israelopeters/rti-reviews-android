package com.example.rtireviews.ui

import com.example.rtireviews.data.Review
import com.example.rtireviews.data.TestData

data class ReviewsListUiState(
    val currentReviewsList: List<Review> = TestData.generateReviewsData()
)
