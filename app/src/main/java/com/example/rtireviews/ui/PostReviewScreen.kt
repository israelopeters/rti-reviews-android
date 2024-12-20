package com.example.rtireviews.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
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
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.rtireviews.R
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun PostReviewScreen(
    viewModel: ReviewViewModel = viewModel(),
    onSubmitButtonClicked: () -> Unit,
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
    Surface(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxSize()
        ) {
            // Image picker
            ReviewPostImageDisplay({ })

            // New review post form
            PostReviewForm(
                reviewDto = reviewPost,
                onSubmitButtonClicked = onSubmitButtonClicked
            )
        }
    }
}

@Composable
fun ReviewPostImageDisplay(
    onImageSelected: (Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    var imageUri: Uri by rememberSaveable { mutableStateOf("".toUri()) }
    // Register a photo picker activity launcher to select only a single item
    val pickMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            imageUri = uri
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    Column {
        if (imageUri.toString().isNotEmpty() ) {
            AsyncImage(
                model = imageUri,
                contentDescription = stringResource(R.string.review_post_image),
                modifier = modifier.fillMaxWidth().height(250.dp)
            )
        }
        OutlinedButton(
            onClick = {
                // Launch the photo picker; user can only choose one image (no other file type)
                pickMedia.launch(
                    PickVisualMediaRequest(PickVisualMedia.ImageOnly)
                )
                // Pass the updated Uri state to the calling Composable
                onImageSelected(imageUri)
            },
            modifier = modifier.padding(8.dp)
        ) {
            Text(stringResource(R.string.select_image))
        }
    }
}

@Composable
fun PostReviewForm(
    reviewDto: ReviewDto,
    onSubmitButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        TextField(
            value = reviewDto.title,
            onValueChange = { reviewDto.title = it },
            label = { Text(stringResource(R.string.title)) },
            maxLines = 2,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
        )
        TextField(
            value = reviewDto.body,
            onValueChange = { reviewDto.body = it },
            label = { Text(stringResource(R.string.review_text)) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Button(
            onClick = onSubmitButtonClicked,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
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