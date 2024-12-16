package com.example.rtireviews.ui

import androidx.lifecycle.ViewModel
import com.example.rtireviews.data.Review
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ReviewViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState: StateFlow<ReviewUiState> = _uiState.asStateFlow()

    private val _uiListState = MutableStateFlow(ReviewsListUiState())
    val uiListUiState: StateFlow<ReviewsListUiState> = _uiListState.asStateFlow()

    fun updateUiState(review: Review) {
        _uiState.value = ReviewUiState(currentReview = review)
    }

}