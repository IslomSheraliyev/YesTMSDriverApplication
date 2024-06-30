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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.app_bar.InnerAppBar
import com.yestms.driver.android.components.button.PrimaryButton
import com.yestms.driver.android.components.card.AlertLogItemCard
import com.yestms.driver.android.components.card.LoadCard
import com.yestms.driver.android.components.card.NoPickUpLocationCard
import com.yestms.driver.android.components.card.PickUpLocationCard
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.components.spacer.VerticalSpacer
import com.yestms.driver.android.data.mapper.or0
import com.yestms.driver.android.domain.enums.DriverDetailsLoadStatus
import com.yestms.driver.android.domain.model.loads.LoadModel
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsScreenContent(
    load: LoadModel?,
    onReportProblem: () -> Unit,
    onUpdateLoadStatus: (Int) -> Unit,
    onBackPressed: () -> Unit,
) {

    val red = CustomTheme.colorScheme.red
    val blue = CustomTheme.colorScheme.blue700

    var buttons by remember { mutableStateOf(listOf<ActionButton>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(CustomTheme.colorScheme.grey100)
                .padding(16.dp)
        ) {

            InnerAppBar(
                title = stringResource(id = R.string.driver_details),
                onBackPressed = onBackPressed
            )

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
                                }
                            )
                        )
                    }

                    else -> emptyList()
                }

                LoadCard(
                    id = load.id.or0(),
                    loadId = load.loadId,
                    rate = load.rate.or0(),
                    mileage = load.mileage.or0(),
                    pickUpLocation = load.pickUpLocation,
                    deliveryLocation = load.deliveryLocation,
                    loadStatus = load.loadStatus,
                    isDetailed = true
                )

                VerticalSpacer(dp = 16)

                if (load.pickUpLocation.isNotEmpty())
                    PickUpLocationCard(
                        title = load.pickUpLocation,
                        description = load.pickUpNote,
                        modifier = Modifier.fillMaxWidth()
                    )
                else NoPickUpLocationCard(
                    isApproved = false,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                VerticalSpacer(dp = 32)

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

                VerticalSpacer(weight = 1f)

                VerticalSpacer(dp = 16)

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    maxItemsInEachRow = 2,
                ) {
                    buttons.forEach { button ->
                        PrimaryButton(
                            cornerRadius = 8,
                            text = button.text,
                            color = button.color,
                            onClick = button.action,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            } else ProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
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