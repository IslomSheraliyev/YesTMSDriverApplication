package com.yestms.driver.android.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.YesTMSTheme

@Composable
fun SearchButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = YesTMSTheme.color.blue700
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.search),
            color = YesTMSTheme.color.neutralColors100,
            style = YesTMSTheme.typography.md16pxMedium,
            modifier = Modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 16.dp
                )
        )
    }
}