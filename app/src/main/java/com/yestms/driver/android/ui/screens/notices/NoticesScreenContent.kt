package com.yestms.driver.android.ui.screens.notices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.yestms.driver.android.components.button.SearchButton
import com.yestms.driver.android.components.card.NoResultsFound
import com.yestms.driver.android.components.card.NoticeCard
import com.yestms.driver.android.components.date_picker.DatePickerTextField
import com.yestms.driver.android.components.loader.ProgressIndicator
import com.yestms.driver.android.components.spacer.VerticalSpacer
import com.yestms.driver.android.components.spinner.SortBySpinner
import com.yestms.driver.android.components.text_field.SearchTextField
import com.yestms.driver.android.data.mapper.common.or0
import com.yestms.driver.android.domain.model.notices.NoticeModel

@Composable
fun NoticesScreenContent(
    notices: LazyPagingItems<NoticeModel>,
    onSearchClick: (String, String, String) -> Unit
) {

    var sortBy by rememberSaveable { mutableStateOf("") }
    var createdDate by rememberSaveable { mutableStateOf("") }
    var searchQuery by rememberSaveable { mutableStateOf("") }


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                SortBySpinner(
                    onSelectOption = {
                        sortBy = it
                    }
                )

                VerticalSpacer(dp = 16)

                DatePickerTextField(
                    onDateSelected = {
                        createdDate = it
                    }
                )

                VerticalSpacer(dp = 16)

                SearchTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    }
                )

                VerticalSpacer(dp = 16)

                SearchButton(
                    onClick = {
                        onSearchClick(
                            sortBy,
                            if (createdDate.isNotEmpty()) createdDate
                                .plus("T00:00:00.000Z") else "",
                            searchQuery
                        )
                    }
                )

                VerticalSpacer(dp = 16)
            }
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
                item { NoResultsFound() }
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