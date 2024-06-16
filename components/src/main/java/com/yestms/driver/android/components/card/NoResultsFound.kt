package com.yestms.driver.android.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.VerticalSpacer

@Composable
fun NoResultsFound(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = CustomTheme.colorScheme.grey20040,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_no_result),
                contentDescription = null,
                tint = Color.Unspecified
            )

            VerticalSpacer(dp = 16)

            Text(
                text = stringResource(id = R.string.no_results_found),
                color = CustomTheme.colorScheme.grey600,
                style = CustomTheme.typography.smBold
            )

            VerticalSpacer(dp = 16)

            Text(
                text = stringResource(id = R.string.please_try_again),
                color = CustomTheme.colorScheme.grey400,
                style = CustomTheme.typography.xsMedium
            )
        }
    }
}