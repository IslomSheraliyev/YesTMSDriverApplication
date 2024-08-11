package com.yestms.driver.android.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer

@Composable
fun DownloadBOLButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(YesTMSTheme.color.blue700.copy(alpha = .12f)),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            vertical = 8.dp
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_download),
            contentDescription = null,
            tint = YesTMSTheme.color.blue700
        )

        HorizontalSpacer(dp = 8)

        Text(
            text = stringResource(id = R.string.download_BOL),
            color = YesTMSTheme.color.blue700,
            style = YesTMSTheme.typography.md16pxMedium
        )
    }
}

@Preview
@Composable
fun PRE() {
    DownloadBOLButton {

    }
}