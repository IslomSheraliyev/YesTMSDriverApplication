package com.yestms.driver.android.ui.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yestms.driver.android.ui.dialogs.ReportProblemDialog

@Composable
fun DetailsScreen(
    id: Int,
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val load by viewModel.load.collectAsState(null)
    var reportProblemDialogVisibility by remember { mutableStateOf(false) }
    val problems by viewModel.problems.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getDetails(id)
        viewModel.getProblems()
    }

    ReportProblemDialog(
        visibility = reportProblemDialogVisibility,
        problems = problems,
        onReport = { viewModel.reportProblem(id, it.id) },
        onDismissRequest = { reportProblemDialogVisibility = false }
    )

    DetailsScreenContent(
        load = load,
        onReportProblem = { reportProblemDialogVisibility = true },
        onUpdateLoadStatus = { loadStatusId -> viewModel.updateLoadStatus(id, loadStatusId) },
        onBackPressed = navController::popBackStack
    )
}