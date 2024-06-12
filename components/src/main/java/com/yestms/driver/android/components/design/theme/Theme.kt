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
import com.yestms.driver.android.components.design.color.tmsLightColorScheme
import com.yestms.driver.android.components.design.font.typography

private val lightColorScheme = tmsLightColorScheme()

private val colorPalette = lightColorScheme(
    primary = Color.Transparent,
    secondary = Color.Transparent,
    tertiary = Color.Transparent,
    background = Color.Transparent,
    surface = Color.Transparent,
    onPrimary = Color.Transparent,
    onError = Color.Transparent,
)

private val darkColorPalette = darkColorScheme(
    primary = Color.Transparent,
    secondary = Color.Transparent,
    tertiary = Color.Transparent,
    background = Color.Transparent,
    surface = Color.Transparent,
    onPrimary = Color.Transparent,
    onError = Color.Transparent,
)

@Composable
fun YesTMSDriverApplicationTheme(
    theme: ThemeMode = ThemeMode.DAY,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

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

        darkTheme -> darkColorPalette
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
}