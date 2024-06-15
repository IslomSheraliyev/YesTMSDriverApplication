package com.yestms.driver.android.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.data.enums.auth.AuthLoginDriverExternalIdStatus
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.ui.navigation.safeNavigate

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val externalIdState by viewModel.isUserExternalIdValid.collectAsState()

    LoginScreenContent(
        isError = externalIdState == AuthLoginDriverExternalIdStatus.INVALID,
        onLoginClicked = { value ->
            viewModel.loginDriver(value)
        }
    )

    if (externalIdState == AuthLoginDriverExternalIdStatus.VALID) {
        navController.navigate(
            route = Screen.Main.screenName,
            navOptions = NavOptions.Builder()
                .setPopUpTo(Screen.LoginScreen.screenName, inclusive = true)
                .build()
        )
    }
}
