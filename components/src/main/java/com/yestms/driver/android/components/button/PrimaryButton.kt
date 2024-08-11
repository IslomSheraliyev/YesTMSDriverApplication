package com.yestms.driver.android.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.YesTMSTheme

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    enabled: Boolean = true,
    cornerRadius: Int = 16,
    color: Color = YesTMSTheme.color.blue700,
    disabledColor: Color = YesTMSTheme.color.grey100,
    textColor: Color = YesTMSTheme.color.white,
    disabledTextColor: Color = YesTMSTheme.color.grey400,
    textStyle: TextStyle = YesTMSTheme.typography.md16pxMedium,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = 28.dp,
        vertical = 16.dp
    ),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius.dp),
        modifier = modifier,
        contentPadding = paddingValues,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = disabledColor
        )
    ) {
        Text(
            text = text,
            modifier = textModifier,
            style = textStyle,
            color = if (enabled) textColor else disabledTextColor
        )
    }
}
