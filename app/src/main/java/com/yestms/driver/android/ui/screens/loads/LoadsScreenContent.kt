package com.yestms.driver.android.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yestms.driver.android.components.app_bar.MainAppBar
import com.yestms.driver.android.components.design.theme.CustomTheme

@Composable
fun LoadsScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomTheme.colorScheme.white)
            .systemBarsPadding()
    ) {

    }
}