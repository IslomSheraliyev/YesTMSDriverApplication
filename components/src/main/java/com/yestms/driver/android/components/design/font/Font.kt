package com.yestms.driver.android.components.design.font

import androidx.annotation.FontRes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.yestms.driver.android.components.R.font.helvetica_regular as regular
import com.yestms.driver.android.components.R.font.helvetica_medium as medium
import com.yestms.driver.android.components.R.font.helvetica_bold as bold

val typography = CustomFont(
    md16pxRegular = createText(
        font = regular,
        size = 16.sp,
        weight = FontWeight.W400,
        lineHeight = 24.sp
    ),
    md16pxMedium = createText(
        font = medium,
        size = 16.sp,
        weight = FontWeight.W500,
        lineHeight = 24.sp
    ),
    sm14pxRegular = createText(
        font = regular,
        size = 14.sp,
        weight = FontWeight.W400,
        lineHeight = 24.sp
    ),
    xsMedium = createText(
        font = medium,
        size = 12.sp,
        weight = FontWeight.W500,
        lineHeight = 18.sp
    ),
    xs24pxMedium = createText(
        font = medium,
        size = 24.sp,
        weight = FontWeight.W500,
        lineHeight = 32.sp
    ),
    smMedium = createText(
        font = medium,
        size = 14.sp,
        weight = FontWeight.W500,
        lineHeight = 20.sp
    ),
    smBold = createText(
        font = bold,
        size = 14.sp,
        weight = FontWeight.W700,
        lineHeight = 20.sp
    ),
    lg18pxMedium = createText(
        font = medium,
        size = 18.sp,
        weight = FontWeight.W500,
        lineHeight = 28.sp
    )
)

private fun createText(
    @FontRes font: Int,
    size: TextUnit,
    weight: FontWeight,
    lineHeight: TextUnit
) = TextStyle(
    fontFamily = FontFamily(Font(font)),
    fontSize = size,
    fontWeight = weight,
    lineHeight = lineHeight
)

val LocalCustomFont = staticCompositionLocalOf { typography }
