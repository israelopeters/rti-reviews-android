package com.example.rtireviews.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rtireviews.R
import com.example.rtireviews.data.ApiRepository
import com.example.rtireviews.service.ReviewViewModel
import com.example.rtireviews.service.UserViewModel
import com.example.rtireviews.ui.theme.RTIReviewsTheme

@Composable
fun WelcomeScreen(
    userViewModel: UserViewModel,
    onLogInClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppDetails(
            image = R.drawable.welcome_screen_image,
            header = R.string.rti_reviews
        )
        AppLogin(
            userViewModel,
            onLogInClicked = onLogInClicked
        )
        AppSignUp(onSignUpClicked = onSignUpClicked)
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
            color = MaterialTheme.colorScheme.onBackground,
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
                .size(240.dp)
                .padding(8.dp)
                .clip(MaterialTheme.shapes.medium)
        )
    }
}

@Composable
fun AppLogin(
    userViewModel: UserViewModel,
    onLogInClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(24.dp)
    ) {
        TextField(
            value = userViewModel.email,
            onValueChange = { userViewModel.updateEmail(it) },
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        TextField(
            value = userViewModel.password,
            onValueChange = { userViewModel.updatePassword(it) },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        Button(
            onClick = onLogInClicked,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(stringResource(R.string.log_in))
        }
    }
}

@Composable
fun AppSignUp(
    onSignUpClicked: () -> Unit,
    modifier: Modifier =  Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.not_yet_registered),
            color = MaterialTheme.colorScheme.onBackground
        )

        OutlinedButton(
            onClick = onSignUpClicked,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(stringResource(R.string.sign_up))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppDetailsPreview() {
    RTIReviewsTheme {
        AppDetails(
            R.drawable.welcome_screen_image,
            R.string.rti_reviews
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppLoginPreview() {
    val apiRepository = ApiRepository()
    RTIReviewsTheme {
        AppLogin(UserViewModel(apiRepository), onLogInClicked = { })
    }
}

@Preview(showBackground = true)
@Composable
fun AppSignUpPreview() {
    RTIReviewsTheme {
        AppSignUp(onSignUpClicked = { })
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
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
fun WelcomeScreenPreview() {
    RTIReviewsTheme {
        WelcomeScreen(
            UserViewModel(),
            onLogInClicked = { },
            onSignUpClicked = { }
        )
    }
}
