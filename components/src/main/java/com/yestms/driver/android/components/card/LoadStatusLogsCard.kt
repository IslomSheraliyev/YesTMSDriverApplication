package com.yestms.driver.android.components.card


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer
import com.yestms.driver.android.components.utils.DateConverter
import com.yestms.driver.android.components.utils.DottedShape
import com.yestms.driver.android.domain.model.loads.LoadStatusLogsItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoadStatusLogsCard(
    logs: List<LoadStatusLogsItem>,
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
            containerColor = YesTMSTheme.color.neutralColors100
        )
    ) {

        FlowColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            logs.forEachIndexed { index, log ->
                if (index != 0)
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .padding(horizontal = 7.5.dp)
                            .width(1.dp)
                            .background(
                                color = YesTMSTheme.color.blue300,
                                shape = DottedShape(6.dp)
                            )
                    )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(
                                color = YesTMSTheme.color.blue700,
                                shape = CircleShape
                            )
                    )

                    HorizontalSpacer(dp = 12)

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = log.loadStatusLogsItemLoadStatus.name,
                            color = YesTMSTheme.color.grey800,
                            style = YesTMSTheme.typography.smBold
                        )

                        Text(
                            text = DateConverter.getNoticeDate(log.createdAt),
                            color = YesTMSTheme.color.grey300,
                            style = YesTMSTheme.typography.xsMedium
                        )
                    }

                    HorizontalSpacer(weight = 1f)

                    Text(
                        text = DateConverter.getNoticeTime(log.createdAt),
                        color = YesTMSTheme.color.grey300,
                        style = YesTMSTheme.typography.xsMedium
                    )
                }

                if (index != logs.lastIndex)
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .padding(horizontal = 7.5.dp)
                            .width(1.dp)
                            .background(
                                color = YesTMSTheme.color.blue300,
                                shape = DottedShape(6.dp)
                            )
                    )
            }
        }
    }
}