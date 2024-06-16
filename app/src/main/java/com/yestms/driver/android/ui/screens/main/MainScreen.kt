package com.yestms.driver.android.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yestms.driver.android.data.enums.auth.AuthCheckTokenStatus
import com.yestms.driver.android.data.enums.auth.AuthLoginDriverExternalIdStatus
import com.yestms.driver.android.ui.dialogs.LoadingDialog
import com.yestms.driver.android.ui.screens.login.LoginScreenContent

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val tokenStatus by viewModel.tokenStatus.collectAsState()
    val refreshing by viewModel.isRefreshing.collectAsState()
    val externalIdState by viewModel.isUserExternalIdValid.collectAsState()

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
            LoginScreenContent(
                isError = externalIdState == AuthLoginDriverExternalIdStatus.INVALID,
                onLoginClicked = { value ->
                    viewModel.loginDriver(value)
                }
            )
        }

        AuthCheckTokenStatus.IDLE -> {
            if (refreshing) {
                LoadingDialog(isVisible = true)
            }
        }
    }
}
