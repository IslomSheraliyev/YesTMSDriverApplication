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
import com.yestms.driver.android.components.prefabs.StatusComponent
import com.yestms.driver.android.domain.model.loads.get.LoadModel
import com.yestms.driver.android.components.R.font.helvetica_medium as medium
import com.yestms.driver.android.components.R.font.helvetica_regular as regular

@Composable
fun LoadCard(
    load: LoadModel,
    modifier: Modifier = Modifier
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
        )
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
                            append(load.rate.toString())
                            append(" - ")
                            append(load.mileage.toString())
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
                            val decimal = DecimalFormat("#.##")
                            append("$")
                            append(decimal.format(load.rate / load.mileage.toDouble()))
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
                    text = load.pickUpLocation,
                    color = CustomTheme.colorScheme.grey800,
                    style = CustomTheme.typography.smMedium,
                    modifier = Modifier.weight(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_transit),
                    contentDescription = null,
                )

                Text(
                    text = load.deliveryLocation,
                    color = CustomTheme.colorScheme.grey800,
                    style = CustomTheme.typography.smMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            StatusComponent(
                color = Color(GraphicsColor.parseColor(load.loadStatus.color)),
                text = load.loadStatus.name,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}