package com.yestms.driver.android.components.design.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yestms.driver.android.components.design.color.blue700Light
import com.yestms.driver.android.components.design.color.tmsLightColorScheme
import com.yestms.driver.android.components.design.font.typography
import com.yestms.driver.android.data.enums.ThemeMode
import com.yestms.driver.android.data.local.AppPreferences

private val lightColorScheme = tmsLightColorScheme()

private val colorPalette = lightColorScheme(
    primary = blue700Light,
    secondary = Color.Yellow,
    tertiary = Color.Green,
    background = Color.Gray,
    surface = Color.Yellow,
    onPrimary = Color.Green,
    onError = Color.Magenta,
)

private val darkColorPalette = darkColorScheme(
    primary = Color.Blue,
    secondary = Color.Blue,
    tertiary = Color.Blue,
    background = Color.Blue,
    surface = Color.Yellow,
    onPrimary = Color.Green,
    onError = Color.Magenta,
)

@Composable
fun YesTMSDriverApplicationTheme(
    theme: ThemeMode = ThemeMode.DAY,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val uiController = rememberSystemUiController()

    val darkTheme = when (theme) {
        ThemeMode.DAY -> false
        ThemeMode.NIGHT -> true
    }

    val customColorScheme = if (darkTheme) lightColorScheme else lightColorScheme

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> colorPalette
        else -> colorPalette
    }

    CustomTheme(
        colorScheme = customColorScheme,
        typography = typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }

    uiController.setSystemBarsColor(
        color = Color.Transparent,
        isNavigationBarContrastEnforced = false,
        darkIcons = AppPreferences.themeMode == ThemeMode.DAY
    )
}