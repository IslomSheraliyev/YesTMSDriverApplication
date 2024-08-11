package com.yestms.driver.android.components.loader

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.YesTMSTheme

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = YesTMSTheme.color.blue700,
        strokeWidth = 4.dp,
        trackColor = YesTMSTheme.color.neutralColors100,
        strokeCap = StrokeCap.Square
    )
}