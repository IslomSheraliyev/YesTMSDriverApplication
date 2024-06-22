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
    grey800: Color,
    orange: Color,
    green: Color,
    grey20040: Color,
    grey600: Color,
    blue500: Color,
    grey700: Color,
    darkGreen: Color,
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
    val grey800 by mutableStateOf(grey800, structuralEqualityPolicy())
    val orange by mutableStateOf(orange, structuralEqualityPolicy())
    val green by mutableStateOf(green, structuralEqualityPolicy())
    val grey20040 by mutableStateOf(grey20040, structuralEqualityPolicy())
    val grey600 by mutableStateOf(grey600, structuralEqualityPolicy())
    val blue500 by mutableStateOf(blue500, structuralEqualityPolicy())
    val grey700 by mutableStateOf(grey700, structuralEqualityPolicy())
    val darkGreen by mutableStateOf(darkGreen, structuralEqualityPolicy())
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
    blue100: Color = blue100Light,
    grey800: Color = grey800Light,
    orange: Color = orangeLight,
    green: Color = greenLight,
    grey20040: Color = grey20040Light,
    grey600: Color = grey600Light,
    blue500: Color = blue500Light,
    grey700: Color = grey700Light,
    darkGreen: Color = darkGreenLight
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
    blue100 = blue100,
    grey800 = grey800,
    orange = orange,
    green = green,
    grey20040 = grey20040,
    grey600 = grey600,
    blue500 = blue500,
    grey700 = grey700,
    darkGreen = darkGreen
)

val LocalCustomColorScheme = staticCompositionLocalOf { tmsLightColorScheme() }
