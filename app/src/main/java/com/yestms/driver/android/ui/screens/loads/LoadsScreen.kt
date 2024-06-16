package com.yestms.driver.android.ui.screens.loads

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun LoadsScreen(
    navController: NavController,
    viewModel: LoadsViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getLoads()
    }

    LoadsScreenContent(
        loads = viewModel.loads.collectAsLazyPagingItems()
    )
}