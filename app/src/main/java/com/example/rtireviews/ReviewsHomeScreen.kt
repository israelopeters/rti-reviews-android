package com.example.rtireviews

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rtireviews.components.ReviewsListItem
import com.example.rtireviews.data.Review
import com.example.rtireviews.ui.theme.RTIReviewsTheme


@Composable
fun ReviewsHomeScreen(
    onFabClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onFabClicked() },
                icon = {
                    Icon(Icons.Filled.Create, "Add new review button.")
                },
                text = { Text(text = "Add Review") },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            )
        },
        modifier = modifier
    ) {innerPadding ->
        ReviewsSection(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ReviewsSection(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier.padding(16.dp),
    ) {
        items(generateReviewsData()) {item ->
            ReviewsListItem(
                reviewItem = item,
                navigateToReviewPost = {}
            )
        }
    }
}

private fun generateReviewsData(): List<Review> {
    val reviewsData: MutableList<Review> = mutableListOf()
    for (i in 1..15) {
        reviewsData.add(
            Review(
                title = "A Hot Lagos Afternoon",
                body = body,
                image = R.drawable.a_hot_lagos_afternoon_cover,
                author = "Israel Peters",
                timePosted = "26 min ago"
            )
        )
    }
    return reviewsData
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
fun ReviewsHomeScreenPreview() {
    RTIReviewsTheme {
        ReviewsHomeScreen(
            onFabClicked = { }
        )
    }
}