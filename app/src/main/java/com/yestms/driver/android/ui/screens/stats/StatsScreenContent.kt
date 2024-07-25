package com.yestms.driver.android.ui.screens.stats

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.check_box.StatsPeriodCheckBox
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer
import com.yestms.driver.android.components.spacer.VerticalSpacer

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatsScreenContent(
    refreshing: Boolean,
    refreshState: PullRefreshState,
    earnings: Int,
    miles: Int,
    selectedPeriod: String,
    onPeriodSelected: (String) -> Unit
) {

    val format by rememberSaveable { mutableStateOf(DecimalFormat("#.##")) }

    val periods = rememberSaveable {
        mapOf(
            "last_7_days" to "Last 7 Days",
            "last_week" to "Last Week",
            "last_month" to "Last Month",
            "begin_of_month" to "Begin of Month"
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                periods.forEach { (key, value) ->
                    item {
                        StatsPeriodCheckBox(
                            text = value,
                            isChecked = key == selectedPeriod,
                            onCheck = {
                                onPeriodSelected(key)
                            }
                        )
                    }
                }
            }

            VerticalSpacer(dp = 32)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                listOf(
                    Triple(R.drawable.ic_earnings, "Earnings:", "$$earnings"),
                    Triple(R.drawable.ic_miles, "Miles:", "$miles/ml"),
                    Triple(
                        R.drawable.ic_cost, "Cost per mile:", "$${
                            format.format(
                                if (earnings != 0 && miles != 0) earnings / miles.toDouble()
                                else 0
                            )
                        }/ml"
                    ),
                ).apply {
                    forEachIndexed { index, (id, title, value) ->
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillParentMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(id = id),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )

                                HorizontalSpacer(dp = 8)

                                Text(
                                    text = title,
                                    style = CustomTheme.typography.lg18pxMedium,
                                    color = CustomTheme.colorScheme.grey700
                                )

                                HorizontalSpacer(weight = 1f)

                                Text(
                                    text = value,
                                    style = CustomTheme.typography.lg18pxMedium,
                                    color = CustomTheme.colorScheme.grey700
                                )
                            }
                        }

                        if (index != this.lastIndex) {
                            item {
                                HorizontalDivider(color = CustomTheme.colorScheme.grey200)
                            }
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = refreshing,
            state = refreshState,
            modifier = Modifier
                .align(Alignment.TopCenter),
            contentColor = CustomTheme.colorScheme.blue500
        )
    }
}

