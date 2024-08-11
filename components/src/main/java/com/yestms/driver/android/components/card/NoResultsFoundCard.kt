package com.yestms.driver.android.components.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.spacer.VerticalSpacer

@Composable
fun NoResultsFound(
    @DrawableRes painter: Int,
    @StringRes title: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = YesTMSTheme.color.grey20040,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(id = painter),
                contentDescription = null,
                tint = Color.Unspecified
            )

            VerticalSpacer(dp = 16)

            Text(
                text = stringResource(id = title),
                color = YesTMSTheme.color.grey600,
                style = YesTMSTheme.typography.smBold
            )

            VerticalSpacer(dp = 16)

            Text(
                text = stringResource(id = description),
                color = YesTMSTheme.color.grey400,
                style = YesTMSTheme.typography.xsMedium
            )
        }
    }
}