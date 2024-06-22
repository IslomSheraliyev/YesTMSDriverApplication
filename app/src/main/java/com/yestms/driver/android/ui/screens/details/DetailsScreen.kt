package com.yestms.driver.android.ui.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DetailsScreen(
    id: Int,
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()

) {
    val load by viewModel.load.collectAsState(null)

    LaunchedEffect(key1 = Unit) {
        viewModel.getDetails(id)
    }


    DetailsScreenContent(
        load = load,
        onBackPressed = navController::popBackStack
    )
}