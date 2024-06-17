package com.yestms.driver.android.ui.screens.notices

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun NoticesScreen(
    viewModel: NoticesViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getNotices()
    }

    NoticesScreenContent(
        notices = viewModel.notices.collectAsLazyPagingItems(),
        onSearchClick = { sortBy, createdDate, searchQuery ->
            viewModel.getNotices(
                sort = sortBy,
                dateFrom = createdDate,
                search = searchQuery
            )
        }
    )
}