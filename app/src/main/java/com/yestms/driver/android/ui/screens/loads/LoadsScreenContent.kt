package com.yestms.driver.android.ui.screens.loads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.card.LoadCard
import com.yestms.driver.android.components.card.NoResultsFound
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.data.mapper.or0
import com.yestms.driver.android.domain.model.loads.LoadsItemModel

@Composable
fun LoadsScreenContent(
    loads: LazyPagingItems<LoadsItemModel>,
    onLoadClick: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ProgressIndicator(
                            modifier = Modifier
                                .size(44.dp)
                        )
                    }
                }
            }

            is LoadState.NotLoading -> {
                items(
                    count = loads.itemCount,
                    key = { loads[it]?.id.or0() }
                ) { index ->
                    loads[index]?.let { notNullLoad ->
                        LoadCard(
                            id = notNullLoad.id,
                            loadId = notNullLoad.loadId,
                            rate = notNullLoad.rate,
                            mileage = notNullLoad.mileage,
                            pickUpLocation = notNullLoad.pickUpLocation,
                            deliveryLocation = notNullLoad.deliveryLocation,
                            loadStatus = notNullLoad.loadStatus,
                            onClick = onLoadClick
                        )
                    }
                }
            }
        }
    }
}