package com.yestms.driver.android.components.prefabs

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(dp: Int) {
    Spacer(modifier = Modifier.height(dp.dp))
}

@Composable
fun ColumnScope.VerticalSpacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}
