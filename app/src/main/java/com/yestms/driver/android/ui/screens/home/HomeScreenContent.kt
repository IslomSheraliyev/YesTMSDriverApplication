package com.yestms.driver.android.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.app_bar.MainAppBar
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.segment.SegmentItem

@Composable
fun HomeScreenContent() {

    var selectedSegmentItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomTheme.colorScheme.white)
            .systemBarsPadding()
    ) {
        MainAppBar(
            modifier = Modifier
                .fillMaxWidth()
        )

        HorizontalDivider(color = CustomTheme.colorScheme.grey200)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                SegmentItem(
                    text = stringResource(id = R.string.loads),
                    isChecked = selectedSegmentItemIndex == 0,
                    index = 0,
                    onSelect = { segmentId -> selectedSegmentItemIndex = segmentId }
                )
            }

            item {
                SegmentItem(
                    text = stringResource(id = R.string.stats),
                    isChecked = selectedSegmentItemIndex == 1,
                    index = 1,
                    onSelect = { segmentId -> selectedSegmentItemIndex = segmentId }
                )
            }
        }

        HorizontalDivider(color = CustomTheme.colorScheme.grey200)

    }
}