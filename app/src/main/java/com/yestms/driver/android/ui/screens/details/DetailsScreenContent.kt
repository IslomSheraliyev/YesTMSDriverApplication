package com.yestms.driver.android.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.card.LoadCard
import com.yestms.driver.android.components.card.NoPickUpLocationCard
import com.yestms.driver.android.components.card.PickUpLocationCard
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.components.spacer.VerticalSpacer
import com.yestms.driver.android.data.mapper.or0
import com.yestms.driver.android.domain.model.loads.LoadModel

@Composable
fun DetailsScreenContent(
    load: LoadModel?,
    onBackPressed: () -> Unit,
) {

    Column(
        modifier = Modifier
            .background(CustomTheme.colorScheme.grey100)
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
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
                text = stringResource(id = R.string.driver_details),
                color = CustomTheme.colorScheme.grey800,
                style = CustomTheme.typography.xs24pxMedium,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        VerticalSpacer(dp = 16)

        if (load != null) {
            LoadCard(
                id = load.id.or0(),
                loadId = load.loadId,
                rate = load.rate.or0(),
                mileage = load.mileage.or0(),
                pickUpLocation = load.pickUpLocation,
                deliveryLocation = load.deliveryLocation,
                loadStatus = load.loadStatus,
                isDetailed = true
            )

            VerticalSpacer(dp = 16)

            if (load.pickUpLocation.isNotEmpty())
                PickUpLocationCard(
                    title = load.pickUpLocation,
                    description = load.pickUpNote,
                    modifier = Modifier.fillMaxWidth()
                )
            else NoPickUpLocationCard(
                isApproved = false,
                modifier = Modifier
                    .fillMaxWidth()
            )

        } else ProgressIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}