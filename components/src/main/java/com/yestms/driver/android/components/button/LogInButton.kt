package com.yestms.driver.android.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.CustomTheme

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = CustomTheme.colorScheme.blue700
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        modifier = modifier,

        ) {
        Text(
            text = stringResource(R.string.login),
            color = CustomTheme.colorScheme.neutralColors100,
            style = CustomTheme.typography.md16pxMedium,
            modifier = Modifier.padding(
                horizontal = 28.dp,
                vertical = 16.dp
            )
        )
    }
}