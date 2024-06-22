package com.yestms.driver.android.components.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.CustomTheme

@Composable
fun NoPickUpLocationCard(
    isApproved: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .border(
                width = 1.dp,
                color = CustomTheme.colorScheme.grey200,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = CustomTheme.colorScheme.white
        )
    ) {

        Column(
            modifier = Modifier
                .padding(
                    vertical = 32.dp,
                    horizontal = 20.dp
                )
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IconBackgroundCard(
                if (isApproved) CustomTheme.colorScheme.darkGreen else CustomTheme.colorScheme.blue500,
            ) { modifier, color ->
                Icon(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.ic_pick_up_location),
                    contentDescription = null,
                    tint = color
                )
            }

            Text(
                text = stringResource(id = R.string.provide_pick_up_location),
                color = if (isApproved) CustomTheme.colorScheme.darkGreen else CustomTheme.colorScheme.grey800,
                textAlign = TextAlign.Center,
                style = CustomTheme.typography.md16pxMedium
            )
        }
    }
}