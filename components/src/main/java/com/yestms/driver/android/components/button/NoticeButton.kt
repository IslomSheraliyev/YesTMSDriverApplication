package com.yestms.driver.android.components.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer

@Composable
fun NoticeButton(
    color: Color,
    @DrawableRes painter: Int,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        color = color.copy(alpha = .12f),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {


        Row(
            modifier = modifier
                .clickable { onClick() }
                .padding(
                    horizontal = 14.dp,
                    vertical = 8.dp
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = painter),
                contentDescription = null,
                tint = color
            )

            HorizontalSpacer(dp = 8)

            Text(
                text = text,
                color = color,
                style = CustomTheme.typography.md16pxMedium
            )
        }
    }
}