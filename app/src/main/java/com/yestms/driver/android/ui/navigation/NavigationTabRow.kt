package com.yestms.driver.android.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.navigation.Screen
import com.yestms.driver.android.components.segment.SegmentItem

@Composable
fun TabRow(
    hasNewNotifications: Boolean,
    modifier: Modifier = Modifier,
    onItemClicked: (Screen) -> Unit,
    items: Map<String, Screen> = Screen.Main.menu,
) {

    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        itemsIndexed(items.entries.toList()) { index, screen ->
            SegmentItem(
                text = screen.key,
                hasNewNotifications,
                isChecked = index == selectedTabIndex
            ) {
                onItemClicked(screen.value)
                selectedTabIndex = index
            }
        }
    }
}