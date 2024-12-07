package com.example.rtireviews.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rtireviews.R
import com.example.rtireviews.data.Review
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun ReviewsListItem(
    reviewItem: Review,
    navigateToReviewPost: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { navigateToReviewPost }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(reviewItem.image),
                contentDescription = stringResource(R.string.review_post_image),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = reviewItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = reviewItem.body,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    modifier = Modifier.padding(8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = reviewItem.author,
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(
                        text = reviewItem.timePosted,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium
                    )
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
fun ReviewsListItemPreview() {
    RTIReviewsTheme {
        ReviewsListItem(
            Review(
                title = "A Hot Lagos Afternoon",
                body = R.string.lorem_ipsum_short_paragraph.toString(),
                image = R.drawable.a_hot_lagos_afternoon_cover,
                author = "Israel Peters",
                timePosted = R.string._26_min_ago.toString()
            ),
            navigateToReviewPost = { }
        )
    }
}

