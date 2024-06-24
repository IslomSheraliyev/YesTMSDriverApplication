package com.yestms.driver.android.components.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.button.NoticeButton
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer
import com.yestms.driver.android.components.utils.DateConverter
import com.yestms.driver.android.domain.model.notifications.NotificationModel

@Composable
fun NotificationCard(
    notificationModel: NotificationModel,
    onDeleteClick: () -> Unit,
    onViewClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = CustomTheme.colorScheme.neutralColors100
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = CustomTheme.colorScheme.grey200,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_with_background),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                HorizontalSpacer(dp = 12)

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = DateConverter.getNoticeDate(notificationModel.createdAt),
                        color = CustomTheme.colorScheme.grey800,
                        style = CustomTheme.typography.smMedium
                    )

                    Text(
                        text = DateConverter.getNoticeTime(notificationModel.createdAt),
                        color = CustomTheme.colorScheme.grey400,
                        style = CustomTheme.typography.xsMedium
                    )
                }

                HorizontalSpacer(weight = 1f)

                StatusCard(
                    color = if (notificationModel.isActive) CustomTheme.colorScheme.green
                    else CustomTheme.colorScheme.orange,

                    text = stringResource(
                        id = if (notificationModel.isActive.not()) R.string.read
                        else R.string.unread
                    ),
                    modifier = Modifier.align(Alignment.Top)
                )
            }

            Text(
                text = notificationModel.title,
                color = CustomTheme.colorScheme.grey400,
                style = CustomTheme.typography.md16pxRegular,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NoticeButton(
                    color = CustomTheme.colorScheme.blue700,
                    painter = R.drawable.ic_eye,
                    text = stringResource(id = R.string.view),
                    modifier = Modifier.weight(1f),
                    onClick = onViewClick
                )

                NoticeButton(
                    color = CustomTheme.colorScheme.red,
                    painter = R.drawable.ic_trash,
                    text = stringResource(id = R.string.delete),
                    modifier = Modifier.weight(1f),
                    onClick = onDeleteClick
                )
            }
        }
    }
}