package com.yestms.driver.android.components.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.domain.model.loads.LoadModel

@Composable
fun LoadDetailsCard(
    loadModel: LoadModel,
    modifier: Modifier = Modifier
) {

    val grey500 = CustomTheme.colorScheme.grey500

    val boldStyle by remember {
        mutableStateOf(
            SpanStyle(
                color = grey500,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp
            )
        )
    }

    val regularStyle by remember {
        mutableStateOf(
            SpanStyle(
                color = grey500,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp
            )
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = CustomTheme.colorScheme.grey200,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = CustomTheme.colorScheme.neutralColors100
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.load_details),
                style = CustomTheme.typography.smBold,
                color = CustomTheme.colorScheme.grey500
            )

            HorizontalDivider(color = CustomTheme.colorScheme.grey200)

            Text(
                text = buildAnnotatedString {
                    withStyle(boldStyle) { append(stringResource(id = R.string.commodity)) }
                    append(" ")
                    withStyle(regularStyle) { append(loadModel.commodity) }
                }
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(boldStyle) { append(stringResource(id = R.string.weight)) }
                    append(" ")
                    withStyle(regularStyle) { append(loadModel.weight) }
                }
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(boldStyle) { append(stringResource(id = R.string.height)) }
                    append(" ")
                    withStyle(regularStyle) { append(loadModel.height) }
                }
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(boldStyle) { append(stringResource(id = R.string.length)) }
                    append(" ")
                    withStyle(regularStyle) { append(loadModel.length) }
                }
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(boldStyle) { append(stringResource(id = R.string.notes_and_addresses)) }
                    append(" ")
                    withStyle(regularStyle) { append(loadModel.notes) }
                }
            )
        }
    }
}