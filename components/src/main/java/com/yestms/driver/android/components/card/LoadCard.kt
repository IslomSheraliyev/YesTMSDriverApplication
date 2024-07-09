package com.yestms.driver.android.components.card

import android.icu.text.DecimalFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import android.graphics.Color as GraphicsColor
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.HorizontalSpacer
import com.yestms.driver.android.domain.model.loads.LoadStatusModel
import com.yestms.driver.android.components.R.font.helvetica_medium as medium
import com.yestms.driver.android.components.R.font.helvetica_regular as regular

@Composable
fun LoadCard(
    id: Int,
    rate: Long,
    mileage: Int,
    pickUpLocation: String,
    deliveryLocation: String,
    loadStatus: LoadStatusModel,
    modifier: Modifier = Modifier,
    onClick: (id: Int) -> Unit = { }
) {
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
        ),
        onClick = { onClick(id) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = CustomTheme.colorScheme.grey800,
                                fontFamily = FontFamily(Font(medium)),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W500,
                                fontStyle = FontStyle.Normal
                            )
                        ) {
                            append("$")
                            append(rate.toString())
                            append(" - ")
                            append(mileage.toString())
                        }

                        withStyle(
                            SpanStyle(
                                color = CustomTheme.colorScheme.grey800,
                                fontFamily = FontFamily(Font(regular)),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400,
                                fontStyle = FontStyle.Normal
                            )
                        ) {
                            append("/ml")
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                )

                HorizontalSpacer(dp = 16)

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = CustomTheme.colorScheme.grey800,
                                fontFamily = FontFamily(Font(medium)),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W700,
                                fontStyle = FontStyle.Normal
                            )
                        ) {
                            val decimal = DecimalFormat("#.##")
                            append("$")
                            append(decimal.format(rate / mileage.toDouble()))
                        }

                        withStyle(
                            SpanStyle(
                                color = CustomTheme.colorScheme.grey800,
                                fontFamily = FontFamily(Font(regular)),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400,
                                fontStyle = FontStyle.Normal
                            )
                        ) {
                            append("/ml")
                        }
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = pickUpLocation.ifEmpty { "Unknown" },
                    color = CustomTheme.colorScheme.grey800,
                    style = CustomTheme.typography.smMedium,
                    modifier = Modifier.weight(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_transit),
                    contentDescription = null,
                )

                Text(
                    text = deliveryLocation.ifEmpty { "Unknown" },
                    color = CustomTheme.colorScheme.grey800,
                    style = CustomTheme.typography.smMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            StatusCard(
                color = Color(GraphicsColor.parseColor(loadStatus.color)),
                text = loadStatus.name.status,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}