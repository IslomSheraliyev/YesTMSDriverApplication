package com.yestms.driver.android.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.ui.navigation.safeNavigate

@Composable
fun LoginScreen(
    navController: NavController
) {

    var isError by rememberSaveable {
        mutableStateOf(false)
    }

    LoginScreenContent(
        isError,
        onLoginClicked = { value ->
            isError = value != "00000"
            if (value == "00000") navController.safeNavigate(Screen.HomeScreen.screenName)
        }
    )
}