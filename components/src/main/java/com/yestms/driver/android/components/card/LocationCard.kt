package com.yestms.driver.android.components.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.YesTMSTheme

@Composable
fun LocationCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = YesTMSTheme.color.grey200,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = YesTMSTheme.color.white
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                color = YesTMSTheme.color.grey500,
                style = YesTMSTheme.typography.smBold
            )

            if (description.isNotEmpty()) {

                HorizontalDivider(color = YesTMSTheme.color.grey200)

                Text(
                    text = description,
                    color = YesTMSTheme.color.grey500,
                    style = YesTMSTheme.typography.md16pxRegular
                )
            }
        }
    }
}