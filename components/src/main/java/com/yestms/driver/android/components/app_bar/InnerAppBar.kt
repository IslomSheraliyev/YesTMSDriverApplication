package com.yestms.driver.android.components.app_bar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.CustomTheme

@Composable
fun InnerAppBar(
    title: String,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Surface(
            shape = CircleShape,
            color = CustomTheme.colorScheme.white,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .border(
                    width = 1.dp,
                    color = CustomTheme.colorScheme.grey200,
                    shape = CircleShape
                ),
            onClick = onBackPressed
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(20.dp)
            )
        }

        Text(
            text = title,
            color = CustomTheme.colorScheme.grey800,
            style = CustomTheme.typography.xs24pxMedium,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}