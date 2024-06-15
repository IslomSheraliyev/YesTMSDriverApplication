package com.yestms.driver.android.components.loader

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.CustomTheme

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = CustomTheme.colorScheme.blue700,
        strokeWidth = 4.dp,
        trackColor = CustomTheme.colorScheme.neutralColors100,
        strokeCap = StrokeCap.Square
    )
}