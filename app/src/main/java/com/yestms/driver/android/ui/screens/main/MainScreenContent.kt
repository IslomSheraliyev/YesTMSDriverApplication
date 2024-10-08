package com.yestms.driver.android.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.yestms.driver.android.components.app_bar.MainAppBar
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.ui.navigation.createScreen
import com.yestms.driver.android.ui.screens.details.DetailsScreen
import com.yestms.driver.android.ui.screens.home.HomeScreen

@Composable
fun MainScreenContent(
    isOnDuty: Boolean,
    onDutyChange: (Boolean) -> Unit,
    mainNavController: NavHostController,
    unreadCount: Int,
    onLogoutClick: () -> Unit,
    updateToSeen: (loadId: Int) -> Unit,
    onDestinationChange: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        onDestinationChange()
    }

    Scaffold(
        containerColor = YesTMSTheme.color.white,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                isOnDuty = isOnDuty,
                onDutyChange = onDutyChange,
                onLogoutClick = onLogoutClick,
                modifier = Modifier.statusBarsPadding()
            )
        },
        content = {
            NavHost(
                navController = mainNavController,
                startDestination = Screen.Main.Home.screenName,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                createScreen(
                    route = Screen.Main.Home
                ) {
                    HomeScreen(
                        navController = mainNavController,
                        unreadCount = unreadCount,
                        updateToSeen = updateToSeen,
                        onDestinationChange = onDestinationChange
                    )
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
                        navController = mainNavController
                    )
                }
            }
        }
    )
}