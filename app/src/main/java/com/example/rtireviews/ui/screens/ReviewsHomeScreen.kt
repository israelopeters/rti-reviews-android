package com.example.rtireviews.ui.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rtireviews.components.ReviewsListItem
import com.example.rtireviews.model.Review
import com.example.rtireviews.service.ReviewViewModel
import com.example.rtireviews.ui.theme.RTIReviewsTheme


@Composable
fun ReviewsHomeScreen(
    reviewsViewModel: ReviewViewModel = viewModel(),
    onReviewItemClicked: () -> Unit,
    onFabClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val reviewsListUiState by reviewsViewModel.uiListUiState.collectAsState()
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
            reviewsViewModel = reviewsViewModel,
            reviewsList = reviewsListUiState.currentReviewsList,
            onReviewItemClick = onReviewItemClicked,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ReviewsSection(
    reviewsViewModel: ReviewViewModel = viewModel(),
    reviewsList: List<Review>,
    onReviewItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier,
    ) {
        items(reviewsList) {item ->
            ReviewsListItem(
                reviewItem = item,
                navigateToReviewPost = {
                    reviewsViewModel.updateUiState(item)
                    onReviewItemClick()
                }
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
            onReviewItemClicked = {},
            onFabClicked = { }
        )
    }
}