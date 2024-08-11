package com.yestms.driver.android.components.text_field

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.spacer.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserIDTextField(
    value: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (text: String) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    Column {

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .clip(
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isError) YesTMSTheme.color.red else YesTMSTheme.color.grey200,
                    shape = RoundedCornerShape(8.dp)
                ),
            textStyle = YesTMSTheme.typography.md16pxRegular.copy(color = YesTMSTheme.color.grey500),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    singleLine = true,
                    enabled = true,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 12.dp),
                    visualTransformation = VisualTransformation.None,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enter_user_id),
                            style = YesTMSTheme.typography.md16pxRegular,
                            color = YesTMSTheme.color.grey400
                        )
                    },
                    trailingIcon = {
                        if (isError)
                            Icon(
                                modifier = Modifier
                                    .size(20.dp),
                                painter = painterResource(id = R.drawable.ic_alert_circle),
                                contentDescription = null,
                                tint = YesTMSTheme.color.red
                            )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = YesTMSTheme.color.grey500,
                        unfocusedTextColor = YesTMSTheme.color.grey500,
                        unfocusedContainerColor = YesTMSTheme.color.white,
                        focusedContainerColor = YesTMSTheme.color.white,
                        cursorColor = YesTMSTheme.color.grey200,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        )

        if (isError) {
            VerticalSpacer(dp = 4)

            Text(
                text = stringResource(id = R.string.user_not_found),
                color = YesTMSTheme.color.red,
                style = YesTMSTheme.typography.sm14pxRegular
            )
        }
    }
}