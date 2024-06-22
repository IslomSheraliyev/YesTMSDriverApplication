package com.yestms.driver.android.ui.screens.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.button.SearchButton
import com.yestms.driver.android.components.card.NoResultsFound
import com.yestms.driver.android.components.card.NoticeCard
import com.yestms.driver.android.components.date_picker.DatePickerTextField
import com.yestms.driver.android.components.design.theme.CustomTheme
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.components.spacer.HorizontalSpacer
import com.yestms.driver.android.components.spacer.VerticalSpacer
import com.yestms.driver.android.components.spinner.SortBySpinner
import com.yestms.driver.android.components.text_field.SearchTextField
import com.yestms.driver.android.data.mapper.or0
import com.yestms.driver.android.domain.model.notifications.NotificationModel

@Composable
fun NotificationsScreenContent(
    notifications: LazyPagingItems<NotificationModel>,
    onClickDeleteAll: () -> Unit,
    onSearchClick: (
        sortBy: String?,
        dateFrom: String?,
        dateTo: String?,
        searchQuery: String?
    ) -> Unit
) {

    var sortBy by rememberSaveable { mutableStateOf("") }
    var dateFrom by rememberSaveable { mutableStateOf("") }
    var dateTo by rememberSaveable { mutableStateOf("") }
    var searchQuery by rememberSaveable { mutableStateOf("") }


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {

        item {
            SortBySpinner(
                onSelectOption = {
                    sortBy = it
                }
            )
        }

        item {
            DatePickerTextField(
                value = dateFrom,
                prefixTextId = R.string.date_from,
                onDateSelected = {
                    dateFrom = it
                }
            )
        }

        item {
            DatePickerTextField(
                value = dateTo,
                prefixTextId = R.string.date_to,
                onDateSelected = {
                    dateTo = it
                }
            )
        }

        item {
            SearchTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                }
            )
        }

        item {
            SearchButton(
                onClick = {
                    onSearchClick(
                        sortBy.ifEmpty { null },
                        if (dateFrom.isNotEmpty()) dateFrom
                            .plus("T00:00:00.000Z") else null,
                        if (dateTo.isNotEmpty()) dateTo
                            .plus("T00:00:00.000Z") else null,
                        searchQuery.ifEmpty { null }
                    )
                }
            )

            VerticalSpacer(dp = 24)
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_notification_bell),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                HorizontalSpacer(dp = 8)

                Text(
                    text = stringResource(id = R.string.notifications),
                    color = CustomTheme.colorScheme.grey700,
                    style = CustomTheme.typography.lg18pxMedium
                )

                HorizontalSpacer(weight = 1f)

                Button(
                    onClick = onClickDeleteAll,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomTheme.colorScheme.grey200
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.delete_all),
                        color = CustomTheme.colorScheme.grey400,
                        style = CustomTheme.typography.md16pxMedium,
                        modifier = Modifier

                    )
                }

            }
        }

        item {
            HorizontalDivider(color = CustomTheme.colorScheme.grey200)
        }

        when (notifications.loadState.refresh) {
            is LoadState.Error -> {
                item {
                    NoResultsFound(
                        painter = R.drawable.ic_no_notices,
                        title = R.string.no_notifications_found,
                        description = R.string.you_do_not_have_notifications
                    )
                }
            }

            is LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ProgressIndicator(
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }
            }

            is LoadState.NotLoading -> {
                if (notifications.itemCount > 0)
                    items(
                        notifications.itemCount,
                        key = { notifications[it]?.id.or0() }
                    ) { index ->
                        notifications[index]?.let { notNull -> NoticeCard(notificationModel = notNull) }
                    }
                else item {
                    NoResultsFound(
                        painter = R.drawable.ic_no_notices,
                        title = R.string.no_notifications_found,
                        description = R.string.you_do_not_have_notifications
                    )
                }
            }
        }
    }
}