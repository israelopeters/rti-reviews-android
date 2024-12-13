package com.example.rtireviews.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rtireviews.components.AddComment
import com.example.rtireviews.components.CommentSection
import com.example.rtireviews.components.ReviewPostDetail
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun ReviewDetailScreen(
    reviewViewModel: ReviewViewModel = viewModel(),
    onFabClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val reviewUiState by reviewViewModel.uiState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ReviewPostDetail(reviewUiState.currentReview)
        AddComment({ })
        CommentSection(reviewUiState.currentReview.comments)
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Composable
fun ReviewDetailScreenPreview() {
    RTIReviewsTheme {
        ReviewDetailScreen(
            reviewViewModel = ReviewViewModel(),
            onFabClicked = { }
        )
    }
}