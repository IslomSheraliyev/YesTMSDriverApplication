package com.yestms.driver.android.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yestms.driver.android.components.navigation.Screen

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.screenName
    ) {
        composable(
            route = "splash"
        ) {

        }
    }
}