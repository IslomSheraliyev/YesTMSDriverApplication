package com.yestms.driver.android.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.button.LoginButton
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.VerticalSpacer
import com.yestms.driver.android.components.text_field.UserIDTextField

@Composable
fun LoginScreenContent(
    isError: Boolean,
    onLoginClicked: (value: String) -> Unit
) {

    var value by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colorScheme.blue50)
            .systemBarsPadding()
            .padding(16.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        VerticalSpacer(weight = 1f)

        Image(
            painter = painterResource(id = R.drawable.ic_logo_slogan),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )

        VerticalSpacer(dp = 24)

        Text(
            text = stringResource(id = R.string.title_enter_user_id),
            color = CustomTheme.colorScheme.grey400,
            style = CustomTheme.typography.medium16pxRegular
        )

        VerticalSpacer(weight = 1f)


        UserIDTextField(
            value = value,
            isError = isError,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            value = it
        }

        VerticalSpacer(dp = 16)

        LoginButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { onLoginClicked(value) }
        )
    }
}