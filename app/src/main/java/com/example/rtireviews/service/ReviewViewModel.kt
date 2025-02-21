package com.example.rtireviews.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rtireviews.data.ApiRepository
import com.example.rtireviews.model.Review
import com.example.rtireviews.data.TestData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState: StateFlow<ReviewUiState> = _uiState.asStateFlow()

    private val _uiListState = MutableStateFlow(ReviewsListUiState())
    val uiListUiState: StateFlow<ReviewsListUiState> = _uiListState.asStateFlow()

    private val _uiNewReviewState = MutableStateFlow(NewReviewUiState())
    val uiNewReviewState: StateFlow<NewReviewUiState> = _uiNewReviewState.asStateFlow()

    init {
        //fetchRemoteReview()
    }

    fun updateUiState(review: Review) {
        _uiState.value = ReviewUiState(currentReview = review)
    }

    fun updateUiListState(newReview: NewReviewUiState) {
        TestData.saveNewReview(newReview)
        _uiListState.value = ReviewsListUiState(currentReviewsList = TestData.getReviewsData())
    }

    private fun fetchRemoteReview(id: Long = 2) {
        viewModelScope.launch {
            val remoteReview = apiRepository.getSingleReview(id)
            _uiState.value = ReviewUiState(remoteReview)
        }
    }
}