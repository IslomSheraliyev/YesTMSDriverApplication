package com.yestms.driver.android.ui.screens.loads

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.yestms.driver.android.components.card.InfoCard
import com.yestms.driver.android.domain.model.loads.get.LoadModel

@Composable
fun LoadsScreenContent(
    loads: LazyPagingItems<LoadModel>,
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(
            count = loads.itemCount,
            key = { loads[it]?.loadId.orEmpty() }
        ) { index ->
            loads[index]?.let { notNull -> InfoCard(load = notNull) }
        }
    }
}