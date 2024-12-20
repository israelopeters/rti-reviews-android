package com.example.rtireviews.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rtireviews.R
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun PostReviewScreen(
    viewModel: ReviewViewModel = viewModel(),
    onSubmitButtonClicked: (ReviewDto) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 16.dp).fillMaxSize()
        ) {
            // Image picker

            // New review post form
            PostReviewForm(onSubmitButtonClicked)
        }
    }
}

@Composable
fun PostReviewForm(
    onSubmitButtonClicked: (ReviewDto) -> Unit,
    modifier: Modifier = Modifier
) {
    var reviewTitle by rememberSaveable{ mutableStateOf("") }
    var reviewBody by rememberSaveable{ mutableStateOf("") }
    var reviewPost by rememberSaveable{
        mutableStateOf(
            ReviewDto(
                title = reviewTitle,
                body = reviewBody,
                image = ""
            )
        )
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        TextField(
            value = reviewTitle,
            onValueChange = { reviewTitle = it },
            label = { Text(stringResource(R.string.title)) },
            maxLines = 2,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
        )
        TextField(
            value = reviewBody,
            onValueChange = { reviewBody = it },
            label = { Text(stringResource(R.string.review_text)) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Button(
            onClick = { onSubmitButtonClicked(reviewPost) },
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.submit_review))
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
fun PostReviewFormPreview() {
    RTIReviewsTheme {
        PostReviewForm(onSubmitButtonClicked = { })
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
fun PostReviewScreenPreview() {
    RTIReviewsTheme {
        PostReviewScreen(onSubmitButtonClicked = {})
    }
}