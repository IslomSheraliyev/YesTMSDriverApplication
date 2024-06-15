package com.yestms.driver.android.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yestms.driver.android.components.app_bar.MainAppBar
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.ui.screens.loads.LoadsScreen

@Composable
fun MainScreenContent(
    navController: NavController,
    topNavController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        MainAppBar()

        NavHost(
            navController = topNavController,
            startDestination = Screen.Main.Loads.screenName,
            modifier = Modifier
                .weight(1f)
        ) {
            composable(route = Screen.Main.Loads.screenName) {
                LoadsScreen(navController = navController)
            }
        }
    }
}