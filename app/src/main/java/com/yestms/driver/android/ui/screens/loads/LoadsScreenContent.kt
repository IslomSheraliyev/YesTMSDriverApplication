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
import androidx.paging.compose.LazyPagingItems
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.card.LoadCard
import com.yestms.driver.android.components.card.NoResultsFound
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.data.mapper.or0
import com.yestms.driver.android.domain.model.loads.LoadModel

@Composable
fun LoadsScreenContent(
    loads: LazyPagingItems<LoadModel>,
) {

    if (loads.itemCount > 0)
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(
                count = loads.itemCount,
                key = { loads[it]?.id.or0() }
            ) { index ->
                loads[index]?.let { notNull -> LoadCard(load = notNull) }
            }
        }
    else if (loads.itemCount == 0 && loads.loadState.isIdle)
        NoResultsFound(
            painter = R.drawable.ic_no_loads,
            title = R.string.no_results_found,
            description = R.string.please_try_again

        )
    else Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ProgressIndicator(
            modifier = Modifier
                .size(44.dp)
        )
    }
}