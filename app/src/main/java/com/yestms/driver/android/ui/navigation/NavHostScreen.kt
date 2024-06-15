package com.yestms.driver.android.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.ui.screens.login.LoginScreen
import com.yestms.driver.android.ui.screens.main.MainScreen

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    val fm = LocalFocusManager.current
    navController.addOnDestinationChangedListener { _, _, _ ->
        fm.clearFocus(force = true)
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Main.screenName
    ) {
        composable(
            route = Screen.LoginScreen.screenName
        ) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.Main.screenName
        ) {
            MainScreen(navController = navController)
        }
    }
}