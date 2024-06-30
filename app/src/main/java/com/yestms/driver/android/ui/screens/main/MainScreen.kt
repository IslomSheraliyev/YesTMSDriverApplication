package com.yestms.driver.android.ui.screens.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.data.enums.AuthCheckTokenStatus
import com.yestms.driver.android.data.enums.AuthLoginDriverExternalIdStatus
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.ui.dialogs.LoadingDialog
import com.yestms.driver.android.ui.dialogs.LogoutDialog
import com.yestms.driver.android.ui.navigation.safeNavigate
import com.yestms.driver.android.ui.screens.login.LoginScreenContent

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val tokenStatus by viewModel.tokenStatus.collectAsState()
    val refreshing by viewModel.isRefreshing.collectAsState()
    val externalIdState by viewModel.isUserExternalIdValid.collectAsState()
    val unreadCount by viewModel.unreadCount.collectAsState()
    val isOnDutyState by viewModel.isOnDuty.collectAsState()
    var logoutDialogVisibility by rememberSaveable { mutableStateOf(false) }

    val bottomNavController = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        viewModel.check()
    }

    LogoutDialog(
        visibility = logoutDialogVisibility,
        onLogoutClick = {
            logoutDialogVisibility = false
            AppPreferences.accessToken = ""
            navController.safeNavigate(
                Screen.Main.screenName,
                navOptions = NavOptions.Builder()
                    .setPopUpTo(Screen.Main.screenName, true)
                    .build()
            )
        },
        onDismissRequest = { logoutDialogVisibility = false }
    )

    if (tokenStatus == AuthCheckTokenStatus.VALID || externalIdState == AuthLoginDriverExternalIdStatus.VALID)
        MainScreenContent(
            navController = navController,
            isOnDuty = isOnDutyState,
            topNavController = bottomNavController,
            unreadCount = unreadCount,
            onDutyChange = { isOnDuty -> viewModel.update(isOnDuty) },
            onUpdate = { count -> viewModel.updateUnreadCount(count) },
            updateToSeen = { id -> viewModel.updateLoadStatus(id) },
            onLogoutClick = { logoutDialogVisibility = true },
            onDestinationChange = { viewModel.getUnreadCount() }
        )
    else if (tokenStatus == AuthCheckTokenStatus.INVALID || externalIdState == AuthLoginDriverExternalIdStatus.INVALID)
        LoginScreenContent(
            isError = externalIdState == AuthLoginDriverExternalIdStatus.INVALID,
            onLoginClicked = { value -> viewModel.loginDriver(value) }
        )
    else if (
        refreshing &&
        externalIdState == AuthLoginDriverExternalIdStatus.IDLE
    ) LoadingDialog(isVisible = true)

}
