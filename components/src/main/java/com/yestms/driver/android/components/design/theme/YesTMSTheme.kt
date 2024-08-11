package com.yestms.driver.android.components.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.yestms.driver.android.components.design.color.CustomColorScheme
import com.yestms.driver.android.components.design.color.LocalCustomColorScheme
import com.yestms.driver.android.components.design.font.CustomFont
import com.yestms.driver.android.components.design.font.LocalCustomFont

object YesTMSTheme {
    val color: CustomColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColorScheme.current

    val typography: CustomFont
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomFont.current
}

@Composable
fun YesTMSTheme(
    colorScheme: CustomColorScheme,
    typography: CustomFont,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalCustomColorScheme provides colorScheme,
        LocalCustomFont provides typography
    ) {
        content()
    }
}