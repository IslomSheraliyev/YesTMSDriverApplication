package com.yestms.driver.android.components.spinner

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.design.theme.YesTMSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBySpinner(
    onSelectOption: (String) -> Unit
) {
    val options = listOf("old", "new")
    var selectedOption by remember { mutableStateOf("new") }
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Column {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = YesTMSTheme.color.grey200,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Surface(
                color = YesTMSTheme.color.neutralColors100,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .border(
                        width = 1.dp,
                        color = YesTMSTheme.color.grey200,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .menuAnchor()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 14.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.sort_by, selectedOption),
                        color = YesTMSTheme.color.grey400,
                        style = YesTMSTheme.typography.md16pxRegular
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_drop_down),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .rotate(if (expanded) 180f else 0f)
                    )
                }
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                color = YesTMSTheme.color.grey400,
                                style = YesTMSTheme.typography.md16pxRegular
                            )
                        },
                        onClick = {
                            selectedOption = option
                            onSelectOption(selectedOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
