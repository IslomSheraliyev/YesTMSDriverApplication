package com.yestms.driver.android.components.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.theme.CustomTheme

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textModifier: Modifier = Modifier.padding(vertical = 6.dp),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(0.dp),
    color: Color = CustomTheme.colorScheme.blue700,
    disabledColor: Color = CustomTheme.colorScheme.grey100,
    cornerRadius: Int = 16,
    textStyle: TextStyle = CustomTheme.typography.md16pxMedium,
    textColor: Color = CustomTheme.colorScheme.white,
    disabledTextColor: Color = CustomTheme.colorScheme.grey400,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        elevation = elevation,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = disabledColor
        ),
        shape = RoundedCornerShape(cornerRadius.dp),
        modifier = modifier,
        enabled = enabled
    ) {
        Text(
            text = text,
            modifier = textModifier,
            style = textStyle,
            color = if (enabled) textColor else disabledTextColor
        )
    }
}
