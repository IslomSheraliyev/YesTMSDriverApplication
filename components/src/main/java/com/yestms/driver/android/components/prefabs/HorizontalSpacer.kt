package com.yestms.driver.android.components.prefabs

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(dp: Int) {
    Spacer(modifier = Modifier.width(dp.dp))
}

@Composable
fun RowScope.HorizontalSpacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}
