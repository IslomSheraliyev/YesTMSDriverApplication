package com.yestms.driver.android.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CopyLoadIdCard(
    visibility: Boolean,
    text: String,
    modifier: Modifier = Modifier
) {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    if (visibility)
        Row(
            modifier = modifier
                .background(
                    color = CustomTheme.colorScheme.grey100,
                    shape = RoundedCornerShape(34.dp)
                )
                .padding(
                    vertical = 4.dp,
                    horizontal = 14.dp
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = CustomTheme.colorScheme.grey400,
                style = CustomTheme.typography.smMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            HorizontalSpacer(dp = 8)

            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                Surface(
                    onClick = {
                        clipboardManager.setText(buildAnnotatedString { append(text) })
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_copy),
                        contentDescription = null
                    )
                }
            }
        }
}