package com.yestms.driver.android.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.button.PrimaryButton
import com.yestms.driver.android.components.card.IconBackgroundCard
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.spacer.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutDialog(
    visibility: Boolean,
    onLogoutClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (visibility)
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                securePolicy = SecureFlagPolicy.SecureOn
            ),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = YesTMSTheme.color.white,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CompositionLocalProvider(
                    LocalMinimumInteractiveComponentEnforcement provides false
                ) {
                    Surface(
                        onClick = onDismissRequest,
                        modifier = Modifier
                            .align(Alignment.End),
                        color = Color.Unspecified,
                        shape = CircleShape
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cancel_dialog),
                            contentDescription = null
                        )
                    }
                }

                IconBackgroundCard(
                    padding = 18.dp,
                    color = YesTMSTheme.color.red,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) { modifier, color ->
                    Icon(
                        painter = painterResource(id = R.drawable.ic_alert),
                        contentDescription = null,
                        tint = color,
                        modifier = modifier
                    )
                }

                VerticalSpacer(dp = 24)

                Text(
                    text = stringResource(id = R.string.exit_from_account),
                    style = YesTMSTheme.typography.xs24pxMedium,
                    color = YesTMSTheme.color.black,
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(dp = 16)

                Text(
                    text = stringResource(id = R.string.sure_to_exit),
                    style = YesTMSTheme.typography.md16pxRegular,
                    color = YesTMSTheme.color.grey400,
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(dp = 24)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PrimaryButton(
                        text = stringResource(id = R.string.stay),
                        cornerRadius = 8,
                        color = YesTMSTheme.color.grey100,
                        textColor = YesTMSTheme.color.grey400,
                        modifier = Modifier.weight(1f),
                        onClick = onDismissRequest
                    )

                    PrimaryButton(
                        text = stringResource(id = R.string.exit),
                        cornerRadius = 8,
                        color = YesTMSTheme.color.red,
                        textColor = YesTMSTheme.color.white,
                        modifier = Modifier.weight(1f),
                        onClick = onLogoutClick
                    )
                }
            }
        }
}

