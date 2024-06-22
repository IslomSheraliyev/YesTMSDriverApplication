package com.yestms.driver.android.components.app_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
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
    onLogoutClick: () -> Unit
) {

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
                onClick = onLogoutClick,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 6.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}