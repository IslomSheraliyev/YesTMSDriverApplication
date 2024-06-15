package com.yestms.driver.android.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.data.enums.auth.AuthCheckTokenStatus
import com.yestms.driver.android.ui.dialogs.LoadingDialog

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val tokenStatus by viewModel.tokenStatus.collectAsState()
    val refreshing by viewModel.isRefreshing.collectAsState()

    val bottomNavController = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        viewModel.check()
    }

    when (tokenStatus) {
        AuthCheckTokenStatus.VALID -> {
            MainScreenContent(
                navController = navController,
                topNavController = bottomNavController
            )
        }

        AuthCheckTokenStatus.INVALID -> {
            navController.navigate(
                route = Screen.LoginScreen.screenName,
                navOptions = NavOptions.Builder()
                    .setPopUpTo(Screen.Main.screenName, inclusive = true)
                    .build()
            )
        }

        AuthCheckTokenStatus.IDLE -> {
            if (refreshing) {
                LoadingDialog(isVisible = true)
            }
        }
    }
}
