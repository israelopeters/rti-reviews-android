package com.example.rtireviews.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.rtireviews.data.TestData
import com.example.rtireviews.ui.theme.RTIReviewsTheme


@Composable
fun ReviewsHomeScreen(
    onFabClicked: () -> Unit,
    onReviewItemClick: (Review) -> Unit,
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
        ReviewsSection(
            onReviewItemClick = onReviewItemClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ReviewsSection(
    onReviewItemClick: (Review) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        items(TestData.generateReviewsData()) {item ->
            ReviewsListItem(
                reviewItem = item,
                navigateToReviewPost = { onReviewItemClick(item) }
            )
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
fun ReviewsHomeScreenPreview() {
    RTIReviewsTheme {
        ReviewsHomeScreen(
            onReviewItemClick = { },
            onFabClicked = { }
        )
    }
}