package com.yestms.driver.android.ui.screens.stats

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatsScreen(
    id: Int,
    onBackPressed: () -> Unit,
    viewModel: StatsViewModel = hiltViewModel()
) {

    var selectedPeriod by rememberSaveable { mutableStateOf("last_7_days") }
    val refreshing by viewModel.isRefreshing.collectAsState()

    val refreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = { viewModel.getDetails(id, selectedPeriod) }
    )

    BackHandler(
        onBack = onBackPressed,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getDetails(id, selectedPeriod)
    }

    val uiState by viewModel.uiState.collectAsState()

    StatsScreenContent(
        refreshing = refreshing,
        refreshState = refreshState,
        earnings = uiState.earnings,
        miles = uiState.miles,
        selectedPeriod = selectedPeriod,
        onPeriodSelected = { selectedPeriodParam ->
            selectedPeriod = selectedPeriodParam
            viewModel.getDetails(id, selectedPeriodParam)
        }
    )
}