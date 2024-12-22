package com.example.rtireviews

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rtireviews.components.ReviewsHomeTopBar
import com.example.rtireviews.ui.PostReviewScreen
import com.example.rtireviews.ui.ReviewDetailScreen
import com.example.rtireviews.ui.ReviewViewModel
import com.example.rtireviews.ui.ReviewsHomeScreen
import com.example.rtireviews.ui.SignUpScreen
import com.example.rtireviews.ui.SignUpSuccessScreen
import com.example.rtireviews.ui.WelcomeScreen
import com.example.rtireviews.ui.theme.RTIReviewsTheme

// enums representing app screens
enum class ReviewsScreen(@StringRes val title: Int) {
    Welcome(title = R.string.welcome_screen_header),
    SignUp(title = R.string.sign_up),
    SignUpSuccess(title = R.string.sign_up_success),
    ReviewsHome(title  = R.string.reviews),
    ReviewDetail(title = R.string.review_post),
    PostRevew(title = R.string.post_new_review)
}

@Composable
fun ReviewsApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ReviewsScreen.valueOf(
        backStackEntry?.destination?.route ?: ReviewsScreen.Welcome.name
    )

    Scaffold (
        topBar = {
            if (currentScreen.name != ReviewsScreen.Welcome.name &&
                currentScreen.name != ReviewsScreen.SignUpSuccess.name &&
                currentScreen.name != ReviewsScreen.ReviewsHome.name
                ) {
                ReviewsAppTopBar(
                    currentScreen = currentScreen,
                    canNavigate = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            } else if (
                currentScreen.name == ReviewsScreen.ReviewsHome.name
                ) {
                ReviewsHomeTopBar( navigateUp = { } )
            }

        },
        modifier = modifier
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ReviewsScreen.Welcome.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ReviewsScreen.Welcome.name) {
                WelcomeScreen(
                    onLogInClicked = { },
                    onSignUpClicked = { navController.navigate(ReviewsScreen.SignUp.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
            composable(route = ReviewsScreen.SignUp.name) {
                SignUpScreen(
                    onSignUpClicked = { navController.navigate(ReviewsScreen.SignUpSuccess.name)},
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
            composable(route = ReviewsScreen.SignUpSuccess.name) {
                SignUpSuccessScreen(
                    onLogInClicked = { navController.navigate(ReviewsScreen.ReviewsHome.name)},
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
            composable(route = ReviewsScreen.ReviewsHome.name) {
                ReviewsHomeScreen(
                    onFabClicked = { navController.navigate(ReviewsScreen.PostRevew.name) },
                    onReviewItemClicked = { navController.navigate(ReviewsScreen.ReviewDetail.name)},
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
            composable(route = ReviewsScreen.ReviewDetail.name) {
                ReviewDetailScreen(
                    onFabClicked = { }
                )
            }
            composable(route = ReviewsScreen.PostRevew.name) {
                PostReviewScreen(
                    onSubmitButtonClicked = { navController.navigate(ReviewsScreen.ReviewsHome.name) }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewsAppTopBar(
    currentScreen: ReviewsScreen,
    canNavigate: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(currentScreen.title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (canNavigate) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.arrow_back)
                    )
                }
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier.padding(16.dp).fillMaxWidth()
    )
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
fun ReviewsAppTopBarPreview() {
    RTIReviewsTheme {
        ReviewsAppTopBar(
            currentScreen = ReviewsScreen.SignUp,
            canNavigate = true,
            navigateUp = { },
        )
    }
}
