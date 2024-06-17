package com.yestms.driver.android.ui.screens.notices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.yestms.driver.android.components.R
import com.yestms.driver.android.components.button.SearchButton
import com.yestms.driver.android.components.card.NoResultsFound
import com.yestms.driver.android.components.card.NoticeCard
import com.yestms.driver.android.components.date_picker.DatePickerTextField
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.components.spinner.SortBySpinner
import com.yestms.driver.android.components.text_field.SearchTextField
import com.yestms.driver.android.data.mapper.or0
import com.yestms.driver.android.domain.model.notices.NoticeModel

@Composable
fun NoticesScreenContent(
    notices: LazyPagingItems<NoticeModel>,
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
        }

        when {
            notices.itemCount > 0 -> {
                items(
                    notices.itemCount,
                    key = { notices[it]?.id.or0() }
                ) { index ->
                    notices[index]?.let { notNull -> NoticeCard(noticeModel = notNull) }
                }
            }

            notices.itemCount == 0 && notices.loadState.append.endOfPaginationReached -> {
                item {
                    NoResultsFound(
                        painter = R.drawable.ic_no_notices,
                        title = R.string.no_notifications_found,
                        description = R.string.you_do_not_have_notifications
                    )
                }
            }

            else -> {
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
        }
    }
}