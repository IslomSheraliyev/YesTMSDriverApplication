package com.yestms.driver.android.components.segment

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.CustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentItem(
    text: String,
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
        }
    }
}