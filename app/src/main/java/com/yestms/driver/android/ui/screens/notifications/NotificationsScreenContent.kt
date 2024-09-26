package com.yestms.driver.android.ui.screens.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.yestms.driver.android.components.card.NotificationCard
import com.yestms.driver.android.components.date_picker.DatePickerTextField
import com.yestms.driver.android.components.design.theme.YesTMSTheme
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.components.spacer.HorizontalSpacer
import com.yestms.driver.android.components.spacer.VerticalSpacer
import com.yestms.driver.android.components.spinner.SortBySpinner
import com.yestms.driver.android.components.text_field.SearchTextField
import com.yestms.driver.android.data.mapper.or0
import com.yestms.driver.android.domain.model.notifications.NotificationModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotificationsScreenContent(
    refreshing: Boolean,
    refreshState: PullRefreshState,
    notifications: LazyPagingItems<NotificationModel>,

    sortBy: String?,
    dateFrom: String?,
    dateTo: String?,
    searchQuery: String?,

    onUpdateSort: (String?) -> Unit,
    onUpdateDateFrom: (String?) -> Unit,
    onUpdateDateTo: (String?) -> Unit,
    onUpdateSearchQuery: (String?) -> Unit,

    onClickDeleteAll: () -> Unit,
    onClickDelete: (Int) -> Unit,
    onClickView: (Int) -> Unit,

    onSearchClick: (
        sortBy: String?,
        dateFrom: String?,
        dateTo: String?,
        searchQuery: String?
    ) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {

            item {
                SortBySpinner(onSelectOption = onUpdateSort)
            }

            item {
                DatePickerTextField(
                    value = dateFrom.orEmpty(),
                    prefixTextId = R.string.date_from,
                    onDateSelected = onUpdateDateFrom
                )
            }

            item {
                DatePickerTextField(
                    value = dateTo.orEmpty(),
                    prefixTextId = R.string.date_to,
                    onDateSelected = onUpdateDateTo
                )
            }

            item {
                SearchTextField(
                    value = searchQuery.orEmpty(),
                    placeHolder = stringResource(id = R.string.search_with_dots),
                    trailingIcon = painterResource(id = R.drawable.ic_search),
                    onValueChange = onUpdateSearchQuery
                )
            }

            item {
                SearchButton(
                    onClick = {
                        onSearchClick(
                            sortBy,
                            if (dateFrom.isNullOrBlank()) null else dateFrom.plus("T00:00:00.000Z"),
                            if (dateTo.isNullOrBlank()) null else dateTo.plus("T00:00:00.000Z"),
                            searchQuery
                        )
                    }
                )

                VerticalSpacer(dp = 24)
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                        color = YesTMSTheme.color.grey700,
                        style = YesTMSTheme.typography.lg18pxMedium
                    )

                    HorizontalSpacer(weight = 1f)

                    Button(
                        onClick = onClickDeleteAll,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(YesTMSTheme.color.grey200)
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete_all),
                            color = YesTMSTheme.color.grey400,
                            style = YesTMSTheme.typography.md16pxMedium
                        )
                    }

                }
            }

            item { HorizontalDivider(color = YesTMSTheme.color.grey200) }

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
                            ProgressIndicator(modifier = Modifier.size(44.dp))
                        }
                    }
                }

                is LoadState.NotLoading -> {
                    if (notifications.itemCount > 0)
                        items(
                            notifications.itemCount,
                            key = { notifications[it]?.id.or0() }
                        ) { index ->
                            notifications[index]?.let { notNull ->
                                NotificationCard(
                                    notificationModel = notNull,
                                    onDeleteClick = {
                                        onClickDelete(notifications[index]?.id.or0())
                                    },
                                    onViewClick = {
                                        onClickView(notifications[index]?.id.or0())
                                    }
                                )
                            }
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

        PullRefreshIndicator(
            refreshing = refreshing,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = YesTMSTheme.color.blue500
        )
    }
}