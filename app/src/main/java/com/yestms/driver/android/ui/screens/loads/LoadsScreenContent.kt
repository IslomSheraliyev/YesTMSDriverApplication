package com.yestms.driver.android.ui.screens.loads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.card.LoadCard
import com.yestms.driver.android.components.card.NoResultsFound
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.components.spacer.VerticalSpacer
import com.yestms.driver.android.data.mapper.or0
import com.yestms.driver.android.domain.enums.DriverDetailsLoadStatus
import com.yestms.driver.android.domain.model.loads.LoadsItemModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoadsScreenContent(
    refreshing: Boolean,
    refreshState: PullRefreshState,
    loads: LazyPagingItems<LoadsItemModel>,
    onLoadClick: (id: Int, updateToSeen: Boolean) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 16.dp,
                end = 16.dp,
                start = 16.dp
            ),
            verticalArrangement = Arrangement.Top,
        ) {

            when (loads.loadState.refresh) {
                is LoadState.Error -> {
                    item {
                        NoResultsFound(
                            painter = R.drawable.ic_no_loads,
                            title = R.string.no_results_found,
                            description = R.string.please_try_again
                        )
                    }
                }

                is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            ProgressIndicator(modifier = Modifier.size(44.dp))
                        }
                    }
                }

                is LoadState.NotLoading -> {
                    items(
                        count = loads.itemCount,
                        key = { loads[it]?.id.or0() }
                    ) { index ->
                        loads[index]?.let { notNullLoad ->

                            VerticalSpacer(dp = 16)

                            LoadCard(
                                id = notNullLoad.id,
                                loadId = notNullLoad.loadId,
                                rate = notNullLoad.rate,
                                mileage = notNullLoad.mileage,
                                pickUpLocation = notNullLoad.pickUpLocation,
                                deliveryLocation = notNullLoad.deliveryLocation,
                                loadStatus = notNullLoad.loadStatus,
                                onClick = { id ->
                                    onLoadClick(
                                        id,
                                        notNullLoad.loadStatus.name == DriverDetailsLoadStatus.PendingUnseen
                                    )
                                }
                            )
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