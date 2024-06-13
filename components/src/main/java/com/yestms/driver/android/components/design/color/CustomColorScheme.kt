package com.yestms.driver.android.components.design.color

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

class CustomColorScheme(
    blue50: Color,
    blue700: Color,
    grey400: Color,
    neutralColors100: Color,
    red: Color,
    grey200: Color,
    white: Color,
    grey500: Color,
    grey100: Color,
    grey300: Color,
    blue100: Color,
) {
    val blue50 by mutableStateOf(blue50, structuralEqualityPolicy())
    val blue700 by mutableStateOf(blue700, structuralEqualityPolicy())
    val grey400 by mutableStateOf(grey400, structuralEqualityPolicy())
    val neutralColors100 by mutableStateOf(neutralColors100, structuralEqualityPolicy())
    val red by mutableStateOf(red, structuralEqualityPolicy())
    val grey200 by mutableStateOf(grey200, structuralEqualityPolicy())
    val white by mutableStateOf(white, structuralEqualityPolicy())
    val grey500 by mutableStateOf(grey500, structuralEqualityPolicy())
    val grey100 by mutableStateOf(grey100, structuralEqualityPolicy())
    val grey300 by mutableStateOf(grey300, structuralEqualityPolicy())
    val blue100 by mutableStateOf(blue100, structuralEqualityPolicy())
}

fun tmsLightColorScheme(
    blue50: Color = blue50Light,
    blue700: Color = blue700Light,
    grey400: Color = grey400Light,
    neutralColors100: Color = neutralColors100Light,
    red: Color = redLight,
    grey200: Color = grey200Light,
    white: Color = whiteLight,
    grey500: Color = grey500Light,
    grey100: Color = grey100Light,
    grey300: Color = grey300Light,
    blue100: Color = blue100Light
) = CustomColorScheme(
    blue50 = blue50,
    blue700 = blue700,
    grey400 = grey400,
    neutralColors100 = neutralColors100,
    red = red,
    grey200 = grey200,
    white = white,
    grey500 = grey500,
    grey100 = grey100,
    grey300 = grey300,
    blue100 = blue100
)

val LocalCustomColorScheme = staticCompositionLocalOf { tmsLightColorScheme() }
