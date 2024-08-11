package com.yestms.driver.android.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.color.blue300Light
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.spacer.VerticalSpacer

@Composable
fun UploadImageComponent(
    onClick: () -> Unit,
    isUploaded: Boolean,
    painter: Painter,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false
) {

    val color =
        if (isError) YesTMSTheme.color.red else if (isUploaded) YesTMSTheme.color.green else YesTMSTheme.color.blue700
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = YesTMSTheme.color.blue6
        ),
        modifier = modifier
            .fillMaxWidth()
            .dashedBorder(
                color = blue300Light,
                shape = RoundedCornerShape(12.dp)
            )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            IconBackgroundCard(
                color = color,
                padding = 10.dp,
                alpha = 1f
            ) { modifier, _ ->
                Icon(
                    modifier = modifier,
                    tint = YesTMSTheme.color.white,
                    painter = if (isUploaded) painterResource(id = R.drawable.ic_check) else painter,
                    contentDescription = null
                )
            }

            VerticalSpacer(dp = 16)

            Text(
                text = title,
                color = color,
                style = YesTMSTheme.typography.smBold
            )

            VerticalSpacer(dp = 8)

            Text(
                text = description,
                style = YesTMSTheme.typography.xsMedium,
                color = YesTMSTheme.color.grey400
            )
        }
    }
}


fun Modifier.dashedBorder(
    color: Color,
    shape: Shape,
    strokeWidth: Dp = 1.dp,
    dashWidth: Dp = 4.dp,
    gapWidth: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = this.drawWithContent {
    val outline = shape.createOutline(size, layoutDirection, this)

    val path = Path()
    path.addOutline(outline)

    val stroke = Stroke(
        cap = cap,
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashWidth.toPx(), gapWidth.toPx()),
            phase = 0f
        )
    )

    this.drawContent()

    drawPath(
        path = path,
        style = stroke,
        color = color
    )
}