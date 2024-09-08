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
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val refreshing by viewModel.isRefreshing.collectAsState()
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

    if (
        uiState.tokenStatus == AuthCheckTokenStatus.VALID ||
        uiState.externalIdStatus == AuthLoginDriverExternalIdStatus.VALID
    ) MainScreenContent(
        isOnDuty = uiState.isOnDuty,
        mainNavController = mainNavController,
        unreadCount = uiState.unreadCount,
        onDutyChange = { isOnDuty -> viewModel.update(isOnDuty) },
        updateToSeen = { id -> viewModel.updateLoadStatusToSeen(id) },
        onLogoutClick = { logoutDialogVisibility = true },
        onDestinationChange = { viewModel.getUnreadCount() }
    )
    else if (
        uiState.tokenStatus == AuthCheckTokenStatus.INVALID ||
        uiState.externalIdStatus == AuthLoginDriverExternalIdStatus.INVALID
    ) LoginScreenContent(
        isError = uiState.externalIdStatus == AuthLoginDriverExternalIdStatus.INVALID,
        onLoginClicked = { value -> viewModel.loginDriver(value) }
    )
    else if (
        refreshing &&
        uiState.externalIdStatus == AuthLoginDriverExternalIdStatus.IDLE
    ) LoadingDialog(isVisible = true)
}
