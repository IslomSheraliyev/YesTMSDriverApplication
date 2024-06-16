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
import com.yestms.driver.android.components.R.font.helvetica_medium as medium

val typography = CustomFont(
    medium16pxRegular = createText(
        font = regular,
        size = 16.sp,
        FontStyle.Normal,
        weight = FontWeight.W400,
        lineHeight = 24.sp
    ),
    medium16pxMedium = createText(
        font = medium,
        size = 16.sp,
        FontStyle.Normal,
        weight = FontWeight.W500,
        lineHeight = 24.sp
    ),
    small14pxRegular = createText(
        font = regular,
        size = 14.sp,
        style = FontStyle.Normal,
        weight = FontWeight.W400,
        lineHeight = 24.sp
    ),
    xsMedium = createText(
        font = medium,
        size = 12.sp,
        style = FontStyle.Normal,
        weight = FontWeight.W500,
        lineHeight = 18.sp
    ),
    smMedium = createText(
        font = medium,
        size = 14.sp,
        style = FontStyle.Normal,
        weight = FontWeight.W500,
        lineHeight = 20.sp
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

val LocalCustomFont = staticCompositionLocalOf { typography }
