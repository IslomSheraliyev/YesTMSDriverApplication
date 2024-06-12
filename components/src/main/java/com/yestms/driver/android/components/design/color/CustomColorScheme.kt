package com.yestms.driver.android.components.design.color

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

class CustomColorScheme(
    blue50: Color,
    blue700: Color,
    grey400: Color

) {
    val blue50Light by mutableStateOf(blue50, structuralEqualityPolicy())
    val blue700Light by mutableStateOf(blue700, structuralEqualityPolicy())
    val grey400Light by mutableStateOf(grey400, structuralEqualityPolicy())
}

fun tmsLightColorScheme() = CustomColorScheme(
    blue50 = blue50Light,
    blue700 = blue700Light,
    grey400 = grey400Light
)

val LocalColorScheme = staticCompositionLocalOf { tmsLightColorScheme() }
