package com.example.rtireviews

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { SignUpScreenTopBar() },
        modifier = modifier
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            UserDetailsForm()
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(stringResource(R.string.sign_up))
            }
        }
    }
}

@Composable
fun SignUpScreenTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(16.dp).fillMaxWidth()
    ) {
        IconButton( onClick = {} ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.arrow_back)
            )
        }
        Text(
            text = stringResource(R.string.sign_up),
            style = MaterialTheme.typography.titleLarge
        )
        TextButton( onClick = {} ) {
            Text(stringResource(R.string.cancel))
        }
    }
}

@Composable
fun UserDetailsForm(
    modifier: Modifier = Modifier
) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var country by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(stringResource(R.string.first_name)) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(stringResource(R.string.last_name)) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        TextField(
            value = country,
            onValueChange = { country = it },
            label = { Text(stringResource(R.string.country)) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
    }
}

@Preview(
    showBackground = true,
    //showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Composable
fun SignUpScreenPreview() {
    RTIReviewsTheme {
        SignUpScreen()
    }
}