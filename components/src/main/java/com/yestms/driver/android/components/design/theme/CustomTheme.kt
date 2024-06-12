package com.yestms.driver.android.components.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.yestms.driver.android.components.design.color.CustomColorScheme
import com.yestms.driver.android.components.design.color.LocalColorScheme
import com.yestms.driver.android.components.design.font.CustomFont
import com.yestms.driver.android.components.design.font.LocalFont

object CustomTheme {
    val colorScheme: CustomColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: CustomFont
        @Composable
        @ReadOnlyComposable
        get() = LocalFont.current
}

@Composable
fun CustomTheme(
    colorScheme: CustomColorScheme,
    typography: CustomFont,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalFont provides typography
    ) {
        content()
    }
}