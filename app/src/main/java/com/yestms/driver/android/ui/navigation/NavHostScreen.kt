package com.yestms.driver.android.ui.navigation


import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
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
        createScreen(
            route = Screen.LoginScreen.screenName
        ) {
            LoginScreen(navController)
        }

        createScreen(
            route = Screen.Main.screenName
        ) {
            MainScreen(navController = navController)
        }
    }
}


