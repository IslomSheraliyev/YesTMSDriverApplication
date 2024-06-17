package com.yestms.driver.android.components.app_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.segment.SegmentItem
import com.yestms.driver.android.components.spacer.HorizontalSpacer
import com.yestms.driver.android.components.toggle.OnDutySwitch

@Composable
fun MainAppBar(
    unreadCount: Int,
    loadsVisibility: Boolean,
    statsVisibility: Boolean,
    noticeVisibility: Boolean,
    modifier: Modifier = Modifier,
    onLaunched: () -> Unit,
    onLoadsClick: () -> Unit,
    onStatsClick: () -> Unit,
    onNoticesClick: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        onLaunched()
    }

    var isOnDuty by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_slogan),
                contentDescription = null
            )

            HorizontalSpacer(weight = 1f)

            Text(
                text = stringResource(id = R.string.on_duty),
                color = CustomTheme.colorScheme.grey500,
                style = CustomTheme.typography.md16pxMedium
            )

            HorizontalSpacer(dp = 8)

            OnDutySwitch(
                checked = isOnDuty,
                onCheckedChanged = { checked ->
                    isOnDuty = checked
                }
            )

            HorizontalSpacer(dp = 8)


            IconButton(
                onClick = onNoticesClick,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
            ) {
                Box {
                    Icon(
                        painter = painterResource(
                            id = if (noticeVisibility) R.drawable.ic_notification_bell
                            else R.drawable.ic_x_close
                        ),
                        contentDescription = null,
                        modifier = Modifier,
                        tint = Color.Unspecified
                    )

                    if (unreadCount > 0 && noticeVisibility) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = 3.dp, end = 5.dp)
                                .size(6.dp)
                                .background(
                                    color = CustomTheme.colorScheme.red,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }
    }

    HorizontalDivider(color = CustomTheme.colorScheme.grey200)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            SegmentItem(
                text = stringResource(id = R.string.loads),
                isChecked = loadsVisibility,
                onSelect = onLoadsClick
            )
        }

        item {
            SegmentItem(
                text = stringResource(id = R.string.stats),
                isChecked = statsVisibility,
                onSelect = onStatsClick
            )
        }
    }

    HorizontalDivider(color = CustomTheme.colorScheme.grey200)

}