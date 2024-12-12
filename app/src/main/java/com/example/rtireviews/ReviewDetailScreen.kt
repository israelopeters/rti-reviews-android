package com.example.rtireviews

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rtireviews.components.AddComment
import com.example.rtireviews.components.CommentItem
import com.example.rtireviews.components.ReviewPostDetail
import com.example.rtireviews.data.Comment
import com.example.rtireviews.data.Review
import com.example.rtireviews.data.TestData
import com.example.rtireviews.data.TestData.Companion.bodyAhla
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun ReviewDetailScreen(
    review: Review = TestData.generateSingleReview(),
    onFabClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ReviewPostDetail(review)
        AddComment({ })
        CommentSection(review.comments)
    }
}

@Composable
fun CommentSection(
    commentList: List<Comment>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier.padding(8.dp),
    ) {
        items(commentList) {item ->
            CommentItem(item)
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
            review = Review(
                title = "A Hot Lagos Afternoon",
                body = bodyAhla, // From TestData class
                image = R.drawable.ahla_cover,
                author = "Israel Peters",
                timePosted = "26 min ago",
                comments = TestData.generateCommentsData()
            ),
            onFabClicked = { }
        )
    }
}