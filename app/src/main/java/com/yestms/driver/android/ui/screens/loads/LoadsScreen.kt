package com.yestms.driver.android.ui.screens.loads

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun LoadsScreen(
    viewModel: LoadsViewModel = hiltViewModel(),
    onLoadClick: (id: Int) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getLoads()
    }

    LoadsScreenContent(
        loads = viewModel.loads.collectAsLazyPagingItems(),
        onLoadClick = onLoadClick
    )
}