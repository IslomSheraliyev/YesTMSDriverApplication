package com.yestms.driver.android.ui.screens.loads

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoadsScreen(
    viewModel: LoadsViewModel = hiltViewModel(),
    onLoadClick: (id: Int) -> Unit
) {

    val refreshing by viewModel.isRefreshing.collectAsState()

    val refreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = { viewModel.getLoads() }
    )
    LaunchedEffect(key1 = Unit) {
        viewModel.getLoads()
    }

    LoadsScreenContent(
        refreshing = refreshing,
        refreshState = refreshState,
        loads = viewModel.loads.collectAsLazyPagingItems(),
        onLoadClick = onLoadClick
    )
}