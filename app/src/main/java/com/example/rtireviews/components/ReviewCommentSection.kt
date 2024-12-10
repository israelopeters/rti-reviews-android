package com.example.rtireviews.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rtireviews.R
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun ReviewCommentSection(
    onPostClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AddComment(onPost = onPostClick)
    // CommentList()
}

@Composable
fun AddComment(
    onPost: () -> Unit,
    modifier: Modifier = Modifier
) {
    var commentText by rememberSaveable { mutableStateOf("") }

    Box(modifier = modifier) {
        TextField(
            value = commentText,
            onValueChange = { commentText = it },
            placeholder = {
                Text("Add a comment...")
            },
            colors = TextFieldDefaults.colors(
                MaterialTheme.colorScheme.surface
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_comment),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(onClick = onPost)
                        .padding(end = 8.dp)
                )
            }
        )
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
fun AddCommentPreview() {
    RTIReviewsTheme {
        AddComment({ })
    }
}