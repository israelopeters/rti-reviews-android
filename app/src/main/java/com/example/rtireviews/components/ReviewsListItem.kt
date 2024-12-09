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
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = reviewItem.body,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
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
                body = body,
                image = R.drawable.a_hot_lagos_afternoon_cover,
                author = "Israel Peters",
                timePosted = "26 min ago"
            ),
            navigateToReviewPost = { }
        )
    }
}

private val body = "A Hot Lagos Afternoon by Promise Onyekachukwu is an exceptional and " +
        "tasteful collection of unashamed Nigerian stories. From the captivating" +
        " first story, “A Hot Lagos Afternoon”, where a trip to a woman’s wedding " +
        "quickly turns into her worse nightmare, to “Lagos Living”, where a " +
        "23-year-old quickly lands himself in Nigeria's famous Kirikiri prison " +
        "after a failed attempt at cybercrime, the author writes a mosaic of " +
        "stories about marriage, love and life in the country, Nigeria. This " +
        "debut collection will leave you feeling a whirlwind of emotions but, " +
        "more importantly, realize that just anything is possible in Nigeria."

