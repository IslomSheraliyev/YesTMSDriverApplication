package com.yestms.driver.android.components.app_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer
import com.yestms.driver.android.components.toggle.OnDutySwitch

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    notificationsButtonVisibility: Boolean = true,
    closeButtonVisibility: Boolean = false
) {

    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_slogan),
                contentDescription = null,
                modifier = Modifier
                    .height(36.dp)
            )

            HorizontalSpacer(weight = 1f)

            Text(
                text = stringResource(id = R.string.on_duty),
                color = CustomTheme.colorScheme.grey500,
                style = CustomTheme.typography.medium16pxMedium
            )

            HorizontalSpacer(dp = 8)

            OnDutySwitch(
                checked = isChecked,
                onCheckedChanged = { checked ->
                    isChecked = checked
                }
            )

            if (notificationsButtonVisibility) {
                HorizontalSpacer(dp = 8)

                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification_bell),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(6.dp)
                    )
                }
            }

            if (closeButtonVisibility) {
                HorizontalSpacer(dp = 8)

                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_x_close),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(6.dp)
                    )
                }
            }
        }
    }
}