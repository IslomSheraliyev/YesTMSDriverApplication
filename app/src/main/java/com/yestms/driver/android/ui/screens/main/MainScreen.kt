package com.yestms.driver.android.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.yestms.driver.android.data.enums.AuthCheckTokenStatus
import com.yestms.driver.android.data.enums.AuthLoginDriverExternalIdStatus
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.ui.dialogs.LoadingDialog
import com.yestms.driver.android.ui.dialogs.LogoutDialog
import com.yestms.driver.android.ui.screens.login.LoginScreenContent

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val tokenStatus by viewModel.tokenStatus.collectAsState()
    val refreshing by viewModel.isRefreshing.collectAsState()
    val externalIdState by viewModel.externalIdStatus.collectAsState()
    val unreadCount by viewModel.unreadCount.collectAsState()
    val isOnDutyState by viewModel.isOnDuty.collectAsState()
    var logoutDialogVisibility by rememberSaveable { mutableStateOf(false) }

    val mainNavController = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        viewModel.check()
    }

    LogoutDialog(
        visibility = logoutDialogVisibility,
        onLogoutClick = {
            logoutDialogVisibility = false
            AppPreferences.accessToken = ""
            viewModel.resetTokenStatus()
            viewModel.resetExternalIdStatus()
        },
        onDismissRequest = { logoutDialogVisibility = false }
    )

    if (tokenStatus == AuthCheckTokenStatus.VALID || externalIdState == AuthLoginDriverExternalIdStatus.VALID)
        MainScreenContent(
            isOnDuty = isOnDutyState,
            mainNavController = mainNavController,
            unreadCount = unreadCount,
            onDutyChange = { isOnDuty -> viewModel.update(isOnDuty) },
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
