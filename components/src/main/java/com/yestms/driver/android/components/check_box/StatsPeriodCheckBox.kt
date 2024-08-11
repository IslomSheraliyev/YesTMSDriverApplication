package com.yestms.driver.android.components.check_box

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.YesTMSTheme

@Composable
fun StatsPeriodCheckBox(
    text: String,
    isChecked: Boolean,
    onCheck: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = if (isChecked) YesTMSTheme.color.blue100 else YesTMSTheme.color.white,
        onClick = onCheck
    ) {

        Row(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_checked),
                contentDescription = null,
                tint = if (isChecked) Color.Unspecified else YesTMSTheme.color.white,
                modifier = Modifier
                    .border(
                        shape = CircleShape,
                        width = 1.dp,
                        color = if (isChecked) Color.Unspecified else YesTMSTheme.color.grey200
                    )
            )

            Text(
                text = text,
                style = YesTMSTheme.typography.md16pxMedium,
                color = if (isChecked) YesTMSTheme.color.blue700 else YesTMSTheme.color.grey400
            )
        }
    }
}