package com.yestms.driver.android.components.card

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconBackgroundCard(
    color: Color,
    padding: Dp,
    modifier: Modifier = Modifier,
    alpha: Float = .12f,
    content: @Composable ColumnScope.(
        modifier: Modifier,
        color: Color
    ) -> Unit
) {

    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = alpha)
        )
    ) {
        content(
            modifier.padding(padding),
            color
        )
    }
}