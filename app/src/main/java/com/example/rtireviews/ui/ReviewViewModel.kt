package com.example.rtireviews.ui

import androidx.lifecycle.ViewModel
import com.example.rtireviews.data.Review
import com.example.rtireviews.data.TestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ReviewViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState: StateFlow<ReviewUiState> = _uiState.asStateFlow()

    private val _uiListState = MutableStateFlow(ReviewsListUiState())
    val uiListUiState: StateFlow<ReviewsListUiState> = _uiListState.asStateFlow()

    private val _uiNewReviewState = MutableStateFlow(NewReviewUiState())
    val uiNewReviewState: StateFlow<NewReviewUiState> = _uiNewReviewState.asStateFlow()

    fun updateUiState(review: Review) {
        _uiState.value = ReviewUiState(currentReview = review)
    }

    fun persistNewReview(review: Review) {
        _uiNewReviewState.value = NewReviewUiState(newReview = review)
        TestData.saveNewReview(_uiNewReviewState.value)
    }
}