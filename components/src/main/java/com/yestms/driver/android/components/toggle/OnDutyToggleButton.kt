package com.yestms.driver.android.components.toggle

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.design.color.blue700Light
import com.yestms.driver.android.components.design.color.grey100Light
import com.yestms.driver.android.components.design.color.grey200Light
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import kotlinx.coroutines.launch

@Composable
fun OnDutySwitch(
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChanged: (Boolean) -> Unit
) {

    val switchWidth = 55.dp
    val switchHeight = 24.dp
    val handleSize = 20.dp
    val handlePadding = 2.dp

    val valueToOffset = if (checked) 1f else 0f
    val offset = remember { Animatable(valueToOffset) }
    val scope = rememberCoroutineScope()

    DisposableEffect(checked) {
        if (offset.targetValue != valueToOffset) {
            scope.launch {
                offset.animateTo(valueToOffset, animationSpec = tween(1000))
            }
        }
        onDispose { }
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 3.dp,
                color = lerp(grey200Light, blue700Light, offset.value),
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = lerp(
                    grey100Light,
                    blue700Light,
                    offset.value
                )
            )
            .toggleable(
                value = checked,
                onValueChange = onCheckedChanged,
                role = Role.Switch,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = handlePadding)
                .size(handleSize)
                .offset(x = (switchWidth - handleSize - handlePadding * 2f) * offset.value)
                .background(
                    if (checked) YesTMSTheme.color.white else YesTMSTheme.color.grey300,
                    CircleShape
                )
        )

        Box(
            modifier = Modifier
                .offset(
                    x = (switchWidth - handleSize - handlePadding * 2f) * (1 - offset.value) - if (checked) (-6).dp else 8.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (checked) "ON" else "OFF",
                color = if (checked) YesTMSTheme.color.white else YesTMSTheme.color.grey300,
                style = YesTMSTheme.typography.xsMedium
            )
        }
    }
}
