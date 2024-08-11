package com.yestms.driver.android.components.app_bar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.YesTMSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InnerAppBar(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Surface(
                shape = CircleShape,
                color = YesTMSTheme.color.white,
                modifier = Modifier
                    .align(Alignment.CenterStart),
                onClick = onBackPressed
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = YesTMSTheme.color.grey200,
                            shape = CircleShape
                        )
                        .padding(15.dp)
                )
            }

            content(Modifier.align(Alignment.Center))
        }
    }
}