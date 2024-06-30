package com.yestms.driver.android.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.button.PrimaryButton
import com.yestms.driver.android.components.card.IconBackgroundCard
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.spacer.VerticalSpacer
import com.yestms.driver.android.components.text_field.SearchTextField
import com.yestms.driver.android.domain.model.loads.AlertStatusesItemModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportProblemDialog(
    visibility: Boolean,
    problems: List<AlertStatusesItemModel>,
    onReport: (problem: AlertStatusesItemModel) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedProblemId by rememberSaveable { mutableIntStateOf(-1) }
    var isValidProblem by rememberSaveable { mutableStateOf(false) }

    if (visibility)
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                securePolicy = SecureFlagPolicy.SecureOn
            ),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = CustomTheme.colorScheme.white,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CompositionLocalProvider(
                    LocalMinimumInteractiveComponentEnforcement provides false
                ) {
                    Surface(
                        onClick = onDismissRequest,
                        modifier = Modifier
                            .align(Alignment.End),
                        color = Color.Unspecified,
                        shape = CircleShape
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cancel_dialog),
                            contentDescription = null
                        )
                    }
                }

                IconBackgroundCard(
                    padding = 18.dp,
                    color = CustomTheme.colorScheme.red,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) { modifier, color ->
                    Icon(
                        painter = painterResource(id = R.drawable.ic_alert),
                        contentDescription = null,
                        tint = color,
                        modifier = modifier
                    )
                }

                VerticalSpacer(dp = 24)

                Text(
                    text = stringResource(id = R.string.report_a_problem),
                    style = CustomTheme.typography.xs24pxMedium,
                    color = CustomTheme.colorScheme.black,
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(dp = 24)

                SearchTextField(
                    value = searchQuery,
                    placeHolder = stringResource(id = R.string.search_your_problem),
                    onValueChange = {
                        searchQuery = it
                        isValidProblem = false
                    }
                )

                VerticalSpacer(dp = 8)

                LazyColumn(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.dp,
                            color = CustomTheme.colorScheme.grey200,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    val filteredOptions = problems.filter {
                        it.name.lowercase().contains(searchQuery.lowercase())
                    }
                    val isOptionsVisible = searchQuery.isNotBlank() && filteredOptions.size > 1

                    items(
                        if (isOptionsVisible) filteredOptions else emptyList()
                    ) { problem ->
                        Text(
                            text = problem.name,
                            color = CustomTheme.colorScheme.grey300,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .clickable(
                                    onClick = {
                                        selectedProblemId = problem.id
                                        searchQuery = problem.name
                                        isValidProblem = true
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(
                                        color = CustomTheme.colorScheme.blue500
                                    )
                                )
                                .padding(
                                    vertical = 10.dp,
                                    horizontal = 14.dp
                                )
                        )
                    }
                }

                VerticalSpacer(dp = 16)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PrimaryButton(
                        text = stringResource(id = R.string.cancel),
                        cornerRadius = 8,
                        color = CustomTheme.colorScheme.grey100,
                        textColor = CustomTheme.colorScheme.grey400,
                        modifier = Modifier.weight(1f),
                        onClick = onDismissRequest
                    )

                    PrimaryButton(
                        text = stringResource(id = R.string.report),
                        cornerRadius = 8,
                        enabled = isValidProblem,
                        color = CustomTheme.colorScheme.red,
                        textColor = CustomTheme.colorScheme.white,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onReport(problems.first { it.id == selectedProblemId })
                            onDismissRequest()
                        }
                    )
                }
            }
        }
}