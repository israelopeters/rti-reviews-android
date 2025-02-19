package com.example.rtireviews.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rtireviews.components.AddComment
import com.example.rtireviews.components.CommentItem
import com.example.rtireviews.components.NoComments
import com.example.rtireviews.components.ReviewPostDetail
import com.example.rtireviews.data.ApiRepository
import com.example.rtireviews.service.ReviewViewModel
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun ReviewDetailScreen(
    reviewViewModel: ReviewViewModel,
    onFabClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val reviewUiState by reviewViewModel.uiState.collectAsState()

    Surface(
        modifier = modifier.fillMaxWidth()
    ) {
        LazyColumn {
            item{
                ReviewPostDetail(reviewUiState.currentReview)
            }
            item {
                AddComment({ })
            }
            val commentList = reviewUiState.currentReview.comments
            if (commentList.isEmpty()) {
                item {
                    NoComments()
                }
            } else {
                items(commentList) { item ->
                    CommentItem(item)
                }
            }
        }
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
            reviewViewModel = ReviewViewModel(apiRepository = ApiRepository()),
            onFabClicked = { }
        )
    }
}