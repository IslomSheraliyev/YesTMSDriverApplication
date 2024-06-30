package com.yestms.driver.android.components.text_field

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.yestms.driver.android.components.design.theme.CustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value: String,
    placeHolder: String,
    onValueChange: (text: String) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = CustomTheme.colorScheme.grey200,
                shape = RoundedCornerShape(8.dp)
            ),
        textStyle = CustomTheme.typography.md16pxRegular.copy(color = CustomTheme.colorScheme.black),
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
                        text = placeHolder,
                        style = CustomTheme.typography.md16pxRegular,
                        color = CustomTheme.colorScheme.grey400
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = CustomTheme.colorScheme.black,
                    unfocusedTextColor = CustomTheme.colorScheme.black,
                    unfocusedContainerColor = CustomTheme.colorScheme.white,
                    focusedContainerColor = CustomTheme.colorScheme.white,
                    cursorColor = CustomTheme.colorScheme.grey200,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    )
}