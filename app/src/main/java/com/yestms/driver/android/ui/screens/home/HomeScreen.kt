package com.yestms.driver.android.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    unreadCount: Int,
    navController: NavController,
    updateToSeen: (Int) -> Unit,
    onDestinationChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    val navHostController = rememberNavController()

    HomeScreenContent(
        unreadCount = unreadCount,
        navHostController = navHostController,
        modifier = modifier,
        updateToSeen = updateToSeen,
        navController = navController,
        onDestinationChange = onDestinationChange
    )
}