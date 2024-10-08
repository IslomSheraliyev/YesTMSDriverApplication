package com.yestms.driver.android.ui.screens.stats

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer

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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {

            periods.forEach { (key, value) ->
                item {
                    StatsPeriodCheckBox(
                        text = value,
                        isChecked = key == selectedPeriod,
                        onCheck = { onPeriodSelected(key) },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(32.dp))
            }

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
                    item(span = { GridItemSpan(2) }) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {

                            if (index != 0) Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(id = id),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )

                                HorizontalSpacer(dp = 8)

                                Text(
                                    text = title,
                                    style = YesTMSTheme.typography.lg18pxMedium,
                                    color = YesTMSTheme.color.grey700
                                )

                                HorizontalSpacer(weight = 1f)

                                Text(
                                    text = value,
                                    style = YesTMSTheme.typography.lg18pxMedium,
                                    color = YesTMSTheme.color.grey700
                                )
                            }

                            if (index != this@apply.lastIndex) HorizontalDivider(color = YesTMSTheme.color.grey200)
                        }
                    }
                }
            }
        }


        PullRefreshIndicator(
            refreshing = refreshing,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = YesTMSTheme.color.blue500
        )
    }
}

