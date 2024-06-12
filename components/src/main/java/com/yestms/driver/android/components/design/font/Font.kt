package com.yestms.driver.android.components.design.font

import androidx.annotation.FontRes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.yestms.driver.android.components.R.font.helvetica_regular as regular

val typography = CustomFont(
    medium16pxRegular = createText(
        font = regular,
        size = 16.sp,
        FontStyle.Normal,
        weight = FontWeight.W400,
        lineHeight = 24.sp
    )
)

private fun createText(
    @FontRes font: Int,
    size: TextUnit,
    style: FontStyle = FontStyle.Normal,
    weight: FontWeight,
    lineHeight: TextUnit
) = TextStyle(
    fontFamily = FontFamily(Font(font)),
    fontSize = size,
    fontStyle = style,
    fontWeight = weight,
    lineHeight = lineHeight
)

val LocalFont = staticCompositionLocalOf { typography }
