package com.yestms.driver.android.components.design.font

import androidx.compose.ui.text.TextStyle

data class CustomFont(
    val md16pxRegular: TextStyle = TextStyle(),
    val md16pxMedium: TextStyle = TextStyle(),
    val sm14pxRegular: TextStyle = TextStyle(),
    val xsMedium: TextStyle = TextStyle(),
    val smMedium: TextStyle = TextStyle(),
    val smBold: TextStyle = TextStyle()
)