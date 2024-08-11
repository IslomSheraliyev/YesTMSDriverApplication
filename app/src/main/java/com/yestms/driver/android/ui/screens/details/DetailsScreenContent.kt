package com.yestms.driver.android.ui.screens.details

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.app_bar.InnerAppBar
import com.yestms.driver.android.components.button.DownloadBOLButton
import com.yestms.driver.android.components.button.PrimaryButton
import com.yestms.driver.android.components.card.AlertLogItemCard
import com.yestms.driver.android.components.card.CopyLoadIdCard
import com.yestms.driver.android.components.card.LoadCard
import com.yestms.driver.android.components.card.LoadDetailsCard
import com.yestms.driver.android.components.card.LoadStatusLogsCard
import com.yestms.driver.android.components.card.LocationCard
import com.yestms.driver.android.components.card.NoLocationCard
import com.yestms.driver.android.components.card.UploadImageComponent
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.components.spacer.VerticalSpacer
import com.yestms.driver.android.data.mapper.or0
import com.yestms.driver.android.domain.enums.DriverDetailsLoadStatus
import com.yestms.driver.android.domain.model.loads.LoadModel
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsScreenContent(
    load: LoadModel?,
    isPdfScanned: Boolean,
    isLumperPhotoTaken: Boolean,
    isTrailerPhotoTaken: Boolean,
    onMediaBolClick: () -> Unit,
    onLumperClick: () -> Unit,
    onTrailerPhotosClick: () -> Unit,
    onReportProblem: () -> Unit,
    onUpdateLoadStatus: (Int) -> Unit,
    onBackPressed: () -> Unit,
    onSubmitPaperWork: () -> Unit,
    onDownloadBOLClick: (String, String, String) -> Unit
) {

    val red = YesTMSTheme.color.red
    val blue = YesTMSTheme.color.blue700
    var buttons by remember { mutableStateOf(listOf<ActionButton>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(YesTMSTheme.color.grey100)
                .padding(16.dp)
        ) {

            InnerAppBar(
                onBackPressed = onBackPressed
            ) { modifier ->
                CopyLoadIdCard(
                    text = load?.loadId.orEmpty(),
                    modifier = modifier
                )
            }

            VerticalSpacer(dp = 16)

            if (load != null) {

                buttons = when (load.loadStatus.name) {
                    DriverDetailsLoadStatus.PendingSeen -> {
                        listOf(
                            ActionButton(
                                text = "Reject",
                                color = red,
                                action = {
                                    onUpdateLoadStatus(4)
                                }
                            ),
                            ActionButton(
                                text = "Accept",
                                color = blue,
                                action = {
                                    onUpdateLoadStatus(3)
                                }
                            )
                        )
                    }

                    DriverDetailsLoadStatus.PendingPickUp -> {
                        listOf(
                            ActionButton(
                                text = "Picked up",
                                color = blue,
                                action = {
                                    onUpdateLoadStatus(6)
                                }
                            )
                        )
                    }

                    DriverDetailsLoadStatus.InTransit -> {
                        listOf(
                            ActionButton(
                                text = "Problem",
                                color = red,
                                action = {
                                    onReportProblem()
                                }
                            ),
                            ActionButton(
                                text = "Check in",
                                color = blue,
                                action = {
                                    onUpdateLoadStatus(7)
                                }
                            )
                        )
                    }

                    DriverDetailsLoadStatus.PendingDropOff -> {
                        listOf(
                            ActionButton(
                                text = "Dropped off",
                                color = blue,
                                action = {
                                    onUpdateLoadStatus(8)
                                }
                            )
                        )
                    }

                    DriverDetailsLoadStatus.PendingPaperWork -> {
                        listOf(
                            ActionButton(
                                text = "Submit paperwork",
                                color = blue,
                                action = {
                                    onUpdateLoadStatus(9)
                                    onSubmitPaperWork()
                                }
                            )
                        )
                    }

                    else -> emptyList()
                }

                LoadCard(
                    id = load.id.or0(),
                    rate = load.rate.or0(),
                    mileage = load.mileage.or0(),
                    pickUpLocation = load.pickUpLocation,
                    deliveryLocation = load.deliveryLocation,
                    loadStatus = load.loadStatus,
                    enabled = false
                )

                VerticalSpacer(dp = 16)

                if (load.mediaBOLModels.isNotEmpty()) {
                    DownloadBOLButton(onClick = {
                        onDownloadBOLClick(
                            load.activationLink,
                            "media_bols",
                            load.loadId
                        )
                    })

                    VerticalSpacer(dp = 16)
                }

                LoadDetailsCard(loadModel = load)

                VerticalSpacer(dp = 16)

                if (load.loadStatus.name in setOf(
                        DriverDetailsLoadStatus.PendingUnseen,
                        DriverDetailsLoadStatus.PendingSeen,
                        DriverDetailsLoadStatus.Approved,
                        DriverDetailsLoadStatus.Rejected,
                        DriverDetailsLoadStatus.PendingPickUp
                    )
                ) {
                    if (load.pickUpLocation.isNotBlank()) LocationCard(
                        title = load.pickUpLocation,
                        description = load.pickUpNote
                    ) else NoLocationCard(
                        isApproved = load.loadStatus.name == DriverDetailsLoadStatus.Approved,
                        isPickUp = true
                    )
                } else {
                    if (load.deliveryLocation.isNotBlank()) LocationCard(
                        title = load.deliveryLocation,
                        description = load.deliveryNote
                    ) else NoLocationCard(
                        isApproved = load.loadStatus.name == DriverDetailsLoadStatus.PendingDropOff,
                        isPickUp = false
                    )
                }

                VerticalSpacer(dp = 16)

                if (load.loadStatusLogs.isNotEmpty()) LoadStatusLogsCard(load.loadStatusLogs)

                VerticalSpacer(dp = 16)

                FlowColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    load.loadAlertLogs.forEach { problem ->
                        AlertLogItemCard(
                            modifier = Modifier.fillMaxWidth(),
                            title = problem.loadAlertStatus.name,
                            date = convertIso8601ToFormattedString(problem.createdAt)
                        )
                    }
                }

                if (buttons.isNotEmpty()) VerticalSpacer(weight = 1f)

                if (load.loadStatus.name == DriverDetailsLoadStatus.PendingPaperWork ||
                    load.loadStatus.name == DriverDetailsLoadStatus.PaperWorkFailed
                ) {
                    VerticalSpacer(dp = 24)

                    UploadImageComponent(
                        onClick = onMediaBolClick,
                        isUploaded = isPdfScanned,
                        painter = painterResource(id = R.drawable.ic_media_bol),
                        title = stringResource(id = R.string.media_bol),
                        description = stringResource(id = R.string.open_scanner)
                    )

                    VerticalSpacer(dp = 16)

                    UploadImageComponent(
                        onClick = onLumperClick,
                        isUploaded = isLumperPhotoTaken,
                        painter = painterResource(id = R.drawable.ic_upload),
                        title = stringResource(id = R.string.lumper),
                        description = stringResource(id = R.string.open_gallery_or_camera)
                    )

                    VerticalSpacer(dp = 16)

                    UploadImageComponent(
                        onClick = onTrailerPhotosClick,
                        isUploaded = isTrailerPhotoTaken,
                        painter = painterResource(id = R.drawable.ic_upload),
                        title = stringResource(id = R.string.trailer_photos),
                        description = stringResource(id = R.string.open_gallery_or_camera)
                    )
                }

                if (buttons.isNotEmpty()) VerticalSpacer(dp = 64)
            } else ProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        if ((load != null) &&
            (load.loadStatus.name !in listOf(
                DriverDetailsLoadStatus.PendingPaperWork,
                DriverDetailsLoadStatus.PaperWorkFailed
            ) ||
                    (load.loadStatus.name in listOf(
                        DriverDetailsLoadStatus.PendingPaperWork,
                        DriverDetailsLoadStatus.PaperWorkFailed
                    ) && isPdfScanned))
        )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                maxItemsInEachRow = 2,
            ) {
                buttons.forEach { button ->
                    PrimaryButton(
                        text = button.text,
                        color = button.color,
                        onClick = button.action,
                        cornerRadius = 8,
                        paddingValues = PaddingValues(
                            horizontal = 28.dp,
                            vertical = 16.dp
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
    }
}

data class ActionButton(
    val text: String,
    val color: Color,
    val action: () -> Unit
)

private fun convertIso8601ToFormattedString(iso8601: String): String {
    val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    isoFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = isoFormat.parse(iso8601)

    val desiredFormat = SimpleDateFormat("MMM dd, HH:mm a", Locale.getDefault())
    return date?.let { desiredFormat.format(it) } ?: "Invalid date"
}