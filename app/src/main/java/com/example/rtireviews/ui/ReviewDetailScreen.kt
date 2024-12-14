package com.example.rtireviews.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
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
    val nestedScrollConnection = remember {
        object: NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y.toInt()
                return Offset(0f, available.y)
            }
        }
    }
    val reviewUiState by reviewViewModel.uiState.collectAsState()

    Box(
        modifier = modifier
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