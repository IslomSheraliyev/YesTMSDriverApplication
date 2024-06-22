package com.yestms.driver.android.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer

@Composable
fun StatusCard(
    color: Color,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = color.copy(alpha = .12f),
                shape = RoundedCornerShape(34.dp)
            )
            .padding(
                vertical = 4.dp,
                horizontal = 14.dp
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(
                    color = color,
                    shape = CircleShape
                )
        )

        HorizontalSpacer(dp = 8)

        Text(
            text = text,
            color = color,
            style = CustomTheme.typography.smMedium,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}