package com.example.rtireviews

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        AppDetails(
            image = R.drawable.welcome_screen_image,
            header = R.string.welcome_screen_header,
        )
        AppLogin(onLogIn = { })
        AppSignUp()
    }
}

@Composable
fun AppDetails(
    @DrawableRes image: Int,
    @StringRes header: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 48.dp)
    ) {
        Text(
            text = stringResource(header),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .paddingFromBaseline(
                    top = 8.dp,
                    bottom = 8.dp
                )
        )
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 48.dp)
                .clip(MaterialTheme.shapes.medium)
        )
    }
}

@Composable
fun AppLogin(
    onLogIn: () -> Unit,
    modifier: Modifier = Modifier
) {

    // TODO: Apply ViewModel (Best practice: https://developer.android.com/develop/ui/compose/text/user-input)
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(24.dp)
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        Button(
            onClick = onLogIn,
            modifier = modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Log in")
        }
    }
}

@Composable
fun AppSignUp(
    modifier: Modifier =  Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        Text("Not registered yet?")

        OutlinedButton(
            onClick = { }, //TODO Implement navigation
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Sign-up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppDetailsPreview() {
    RTIReviewsTheme {
        AppDetails(
            R.drawable.welcome_screen_image,
            R.string.welcome_screen_header
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppLoginPreview() {
    RTIReviewsTheme {
        AppLogin(onLogIn = {})
    }
}

@Preview(showBackground = true)
@Composable
fun AppSignUpPreview() {
    RTIReviewsTheme {
        AppSignUp()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    RTIReviewsTheme {
        WelcomeScreen()
    }
}