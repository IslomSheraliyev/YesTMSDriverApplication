package com.yestms.driver.android.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.yestms.driver.android.components.app_bar.MainAppBar
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.ui.navigation.TabRow
import com.yestms.driver.android.ui.navigation.createScreen
import com.yestms.driver.android.ui.navigation.safeNavigate
import com.yestms.driver.android.ui.screens.details.DetailsScreen
import com.yestms.driver.android.ui.screens.loads.LoadsScreen
import com.yestms.driver.android.ui.screens.notifications.NotificationsScreen

@Composable
fun MainScreenContent(
    navController: NavController,
    topNavController: NavHostController,
    unreadCount: Int,
    onDestinationChange: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        onDestinationChange()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        MainAppBar(
            onLogoutClick = {}
        )

        HorizontalDivider(color = CustomTheme.colorScheme.grey200)

        TabRow(
            hasNewNotifications = unreadCount > 0,
            onItemClicked = { screen ->
                onDestinationChange()
                topNavController.navigate(screen.screenName) {
                    popUpTo(topNavController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = false
                    restoreState = true
                }
            }
        )

        HorizontalDivider(color = CustomTheme.colorScheme.grey200)

        NavHost(
            navController = topNavController,
            startDestination = Screen.Main.Loads.screenName,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            createScreen(route = Screen.Main.Loads) {
                LoadsScreen(
                    onLoadClick = { id ->
                        topNavController.safeNavigate(Screen.Main.Details.add(id))
                    }
                )
            }

            createScreen(route = Screen.Main.Notifications) {
                NotificationsScreen()
            }

            createScreen(
                route = Screen.Main.Details,
                arguments = listOf(
                    navArgument(Screen.ID) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                DetailsScreen(
                    id = backStackEntry.arguments?.getInt(Screen.ID) ?: -1,
                    navController = topNavController
                )
            }
        }
    }
}