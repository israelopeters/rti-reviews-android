package com.example.rtireviews.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.rtireviews.R
import com.example.rtireviews.data.TestData
import com.example.rtireviews.service.NewReviewUiState
import com.example.rtireviews.service.ReviewViewModel
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun PostReviewScreen(
    viewModel: ReviewViewModel = viewModel(),
    onSubmitButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var reviewTitle by rememberSaveable { mutableStateOf("") }
    var bookGenre by rememberSaveable { mutableStateOf("") }
    var reviewBody by rememberSaveable { mutableStateOf("") }
    var imageUri: Uri by rememberSaveable { mutableStateOf("".toUri()) }

    // New review post form
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Register a photo picker activity launcher to select only a single item
            val pickMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: $uri")
                    imageUri = uri
                } else {
                    Log.d("PhotoPicker", "No media selected")
                }
            }
            if (imageUri.toString().isNotEmpty()) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = stringResource(R.string.review_post_image),
                    modifier = Modifier.size(width = 375.dp, height = 250.dp)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.filler_book_image), // Image attribution: <a href="https://www.flaticon.com/free-icons/pictures" title="pictures icons">Pictures icons created by Nikita Golubev - Flaticon</a>
                    contentDescription = stringResource(R.string.filler_book_image),
                    modifier = Modifier.size(80.dp)
                )
            }
            OutlinedButton(
                onClick = {
                    // Launch the photo picker; user can only choose one image (no other file type)
                    pickMedia.launch(
                        PickVisualMediaRequest(PickVisualMedia.ImageOnly)
                    )
                },
                modifier = modifier.padding(8.dp)
            ) {
                Text(stringResource(R.string.select_image))
            }

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
                value = bookGenre,
                onValueChange = { bookGenre = it },
                label = { Text(stringResource(R.string.book_genre)) },
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
                onClick = {
                    val reviewTemp = TestData.generateEmptyReview()
                    reviewTemp.title = reviewTitle
                    reviewTemp.body = reviewBody
                    viewModel.updateUiListState(
                        NewReviewUiState(reviewTemp)
                    )// Simulating data persistence
                    onSubmitButtonClicked()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.submit_review))
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
fun PostReviewScreenPreview() {
    RTIReviewsTheme {
        PostReviewScreen(onSubmitButtonClicked = {})
    }
}