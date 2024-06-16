package com.yestms.driver.android.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yestms.driver.android.components.app_bar.MainAppBar
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.ui.navigation.createScreen
import com.yestms.driver.android.ui.screens.loads.LoadsScreen
import com.yestms.driver.android.ui.screens.notices.NoticesScreen

@Composable
fun MainScreenContent(
    navController: NavController,
    topNavController: NavHostController
) {

    var noticeVisibility by rememberSaveable {
        mutableStateOf(true)
    }

    var loadsVisibility by rememberSaveable {
        mutableStateOf(true)
    }

    var statsVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        MainAppBar(
            loadsVisibility = loadsVisibility,
            statsVisibility = statsVisibility,
            noticeVisibility = noticeVisibility,
            onLoadsClick = {
                loadsVisibility = true
                statsVisibility = false
                noticeVisibility = true
                navigateToScreen(topNavController, Screen.Main.Loads)
            },
            onStatsClick = {
                loadsVisibility = false
                statsVisibility = true
                noticeVisibility = true

            },
            onNoticesClick = {
                loadsVisibility = false
                statsVisibility = false
                noticeVisibility = !noticeVisibility
                if (noticeVisibility) {
                    loadsVisibility = true
                    navigateToScreen(topNavController, Screen.Main.Loads)
                } else navigateToScreen(topNavController, Screen.Main.Notices)
            }
        )

        NavHost(
            navController = topNavController,
            startDestination = Screen.Main.Loads.screenName,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            createScreen(route = Screen.Main.Loads) {
                LoadsScreen(navController = topNavController)
            }

            createScreen(route = Screen.Main.Notices) {
                NoticesScreen(navController = topNavController)
            }
        }
    }
}

fun navigateToScreen(navController: NavController, screen: Screen) {
    navController.navigate(screen.screenName) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = false
        restoreState = true
    }
}