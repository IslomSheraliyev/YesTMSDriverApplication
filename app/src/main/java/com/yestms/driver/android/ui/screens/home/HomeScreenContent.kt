package com.yestms.driver.android.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.ui.navigation.TabRow
import com.yestms.driver.android.ui.navigation.createScreen
import com.yestms.driver.android.ui.navigation.safeNavigate
import com.yestms.driver.android.ui.screens.loads.LoadsScreen
import com.yestms.driver.android.ui.screens.notifications.NotificationsScreen
import com.yestms.driver.android.ui.screens.stats.StatsScreen

@Composable
fun HomeScreenContent(
    unreadCount: Int,
    navController: NavController,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    updateToSeen: (Int) -> Unit,
    onDestinationChange: () -> Unit
) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Column(
        modifier = modifier
    ) {
        TabRow(
            hasNewNotifications = unreadCount > 0,
            selectedTabIndex = selectedTabIndex,
            onItemClicked = { screen, index ->
                onDestinationChange()
                navHostController.navigate(screen.screenName) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = false
                    restoreState = true
                }
                selectedTabIndex = index
            }
        )

        HorizontalDivider(color = CustomTheme.colorScheme.grey200)

        NavHost(
            navController = navHostController,
            startDestination = Screen.Main.Home.Loads.screenName,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            createScreen(route = Screen.Main.Home.Loads) {
                LoadsScreen(
                    onLoadClick = { id, updateToSeen ->
                        if (updateToSeen) updateToSeen(id)
                        navController.safeNavigate(Screen.Main.Details.add(id))
                    }
                )
            }

            createScreen(route = Screen.Main.Home.Stats) {
                StatsScreen(
                    id = AppPreferences.currentUserId,
                    onBackPressed = {
                        navHostController.navigate(Screen.Main.Home.Loads.screenName) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = false
                            restoreState = true
                        }
                        selectedTabIndex = 0
                    }
                )
            }

            createScreen(route = Screen.Main.Home.Notifications) {
                NotificationsScreen(
                    onBackPressed = {
                        navHostController.navigate(Screen.Main.Home.Loads.screenName) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = false
                            restoreState = true
                        }
                        selectedTabIndex = 0
                    }
                )
            }
        }
    }
}