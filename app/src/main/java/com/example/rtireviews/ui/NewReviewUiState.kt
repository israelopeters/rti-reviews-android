package com.example.rtireviews.ui

import com.example.rtireviews.model.Review
import com.example.rtireviews.data.TestData

data class NewReviewUiState(
    val newReview: Review = TestData.generateEmptyReview()
)
