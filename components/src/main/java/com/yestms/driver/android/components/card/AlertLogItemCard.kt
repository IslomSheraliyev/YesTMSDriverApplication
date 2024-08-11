package com.yestms.driver.android.components.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.spacer.VerticalSpacer

@Composable
fun AlertLogItemCard(
    title: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.border(
            width = 1.dp,
            color = YesTMSTheme.color.grey200,
            shape = RoundedCornerShape(12.dp)
        ),
        colors = CardDefaults.cardColors(
            containerColor = YesTMSTheme.color.white
        )
    ) {
        Row(
            modifier = modifier
                .padding(20.dp)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            IconBackgroundCard(
                color = YesTMSTheme.color.red,
                padding = 10.dp
            ) { modifier, color ->
                Icon(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.ic_clock_rewind),
                    contentDescription = null,
                    tint = color
                )
            }

            Column {

                Text(
                    text = title,
                    color = YesTMSTheme.color.grey800,
                    style = YesTMSTheme.typography.smMedium
                )

                VerticalSpacer(weight = 1f)

                Text(
                    text = date,
                    color = YesTMSTheme.color.grey400,
                    style = YesTMSTheme.typography.xsMedium
                )
            }
        }
    }
}