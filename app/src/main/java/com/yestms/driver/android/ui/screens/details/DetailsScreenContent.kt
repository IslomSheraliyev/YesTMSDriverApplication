package com.yestms.driver.android.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.app_bar.InnerAppBar
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

        InnerAppBar(
            title = stringResource(id = R.string.driver_details),
            onBackPressed = onBackPressed
        )

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