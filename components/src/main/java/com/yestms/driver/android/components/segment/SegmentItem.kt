package com.yestms.driver.android.components.segment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.CustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentItem(
    text: String,
    hasNewNotifications: Boolean,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onSelect: () -> Unit
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false
    ) {
        Surface(
            modifier = modifier,
            color = if (isChecked)
                CustomTheme.colorScheme.blue100
            else
                CustomTheme.colorScheme.grey100,
            shape = RoundedCornerShape(34.dp),
            onClick = onSelect
        ) {

            Box {
                Text(
                    text = text,
                    color = if (isChecked) CustomTheme.colorScheme.blue500 else CustomTheme.colorScheme.grey400,
                    style = CustomTheme.typography.md16pxRegular,
                    modifier = Modifier
                        .padding(
                            horizontal = 14.dp,
                            vertical = 8.dp
                        )
                )

                if (hasNewNotifications && text == "Notifications") {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp, end = 8.dp)
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