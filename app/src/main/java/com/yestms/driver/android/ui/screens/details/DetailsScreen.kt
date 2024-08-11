package com.yestms.driver.android.ui.screens.details

import android.app.Activity.RESULT_OK
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.yestms.driver.android.ui.activity.MainActivity
import com.yestms.driver.android.ui.dialogs.ReportProblemDialog
import com.yestms.driver.android.ui.navigation.safePopBackStack

@Composable
fun DetailsScreen(
    id: Int,
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current as MainActivity
    val load by viewModel.load.collectAsState(null)
    var reportProblemDialogVisibility by remember { mutableStateOf(false) }
    val problems by viewModel.problems.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    var pdfUri by remember { mutableStateOf<Uri?>(null) }
    var lumperUri by remember { mutableStateOf<Uri?>(null) }
    var trailerPhotoUri by remember { mutableStateOf<Uri?>(null) }

    var openedState by remember { mutableStateOf(OpenedFor.Lumper) }

    var pageCount by remember { mutableIntStateOf(-1) }
    val options by remember {
        mutableStateOf(
            GmsDocumentScannerOptions.Builder()
                .setGalleryImportAllowed(true)
                .setPageLimit(2)
                .setResultFormats(RESULT_FORMAT_PDF)
                .setScannerMode(SCANNER_MODE_FULL)
                .build()
        )
    }
    val scanner by remember { mutableStateOf(GmsDocumentScanning.getClient(options)) }
    val scannerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val res = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
                res?.pdf?.let { pdf ->
                    pdfUri = pdf.uri
                    pageCount = pdf.pageCount
                }
                viewModel.changeMediaBolUploadedState(true)
            }
        }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { imageUri ->
            if (imageUri != null) {
                when (openedState) {
                    OpenedFor.Lumper -> {
                        lumperUri = imageUri
                        viewModel.changeLumperUploadedState(true)
                    }

                    OpenedFor.TrailerPhoto -> {
                        viewModel.changeTrailerPhotoUploadedState(true)
                        trailerPhotoUri = imageUri
                    }
                }
            }
        }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.connect(id)
        viewModel.getDetails(id)
        viewModel.getProblems()
        viewModel.updateLoadState.collect {
            viewModel.getDetails(id)
        }
    }

    ReportProblemDialog(
        visibility = reportProblemDialogVisibility,
        problems = problems,
        onReport = { viewModel.reportProblem(id, it) },
        onDismissRequest = { reportProblemDialogVisibility = false }
    )

    DetailsScreenContent(
        load = load,
        isPdfScanned = uiState.isMediaBolUploaded,
        isLumperPhotoTaken = uiState.isLumperUploaded,
        isTrailerPhotoTaken = uiState.isTrailerPhotoUploaded,
        onMediaBolClick = {
            scanner.getStartScanIntent(context)
                .addOnSuccessListener { intentSender ->
                    scannerLauncher.launch(IntentSenderRequest.Builder(intentSender).build())
                }
                .addOnFailureListener {

                }
        },
        onLumperClick = {
            openedState = OpenedFor.Lumper
            imagePicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        },
        onTrailerPhotosClick = {
            openedState = OpenedFor.TrailerPhoto
            imagePicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        },
        onSubmitPaperWork = {
            pdfUri?.let { pdfUri ->
                viewModel.uploadImages(
                    id, pdfUri, lumperUri, trailerPhotoUri, context.contentResolver
                )
            }
        },
        onReportProblem = { reportProblemDialogVisibility = true },
        onUpdateLoadStatus = { loadStatusId ->
            viewModel.updateLoadStatus(
                id = id,
                loadStatusId = loadStatusId,
                dispatchers = load?.dispatchers.orEmpty()
            )
        },
        onBackPressed = navController::safePopBackStack,
        onDownloadBOLClick = { loadLink, folderLink, loadId ->
            viewModel.downloadData(context, loadLink, folderLink, loadId)
        }
    )
}

private enum class OpenedFor {
    Lumper,
    TrailerPhoto
}