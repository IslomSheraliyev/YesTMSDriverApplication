package com.yestms.driver.android.ui.screens.stats

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StatsScreen(
    id: Int,
    onBackPressed: () -> Unit,
    viewModel: StatsViewModel = hiltViewModel()
) {

    BackHandler(
        onBack = onBackPressed,
    )

    var selectedPeriod by rememberSaveable {
        mutableStateOf("last_7_days")
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getDetails(id, selectedPeriod)
    }

    val uiState by viewModel.uiState.collectAsState()

    StatsScreenContent(
        earnings = uiState.earnings,
        miles = uiState.miles,
        selectedPeriod = selectedPeriod,
        onPeriodSelected = { selectedPeriodParam ->
            selectedPeriod = selectedPeriodParam
            viewModel.getDetails(id, selectedPeriodParam)
        },
        onBackPressed = onBackPressed
    )
}