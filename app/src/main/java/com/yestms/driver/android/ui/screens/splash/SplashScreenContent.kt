package com.yestms.driver.android.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.prefabs.VerticalSpacer

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier
) {

    Column {

        VerticalSpacer(weight = 1f)

        Image(
            painter = painterResource(id = R.drawable.ic_logo_slogan),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )

        VerticalSpacer(dp = 24)

        Text(
            text =
        )

    }
}