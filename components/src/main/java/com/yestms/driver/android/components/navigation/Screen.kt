package com.yestms.driver.android.components.navigation

sealed class Screen(
    val screenName: String
) {
    data object LoginScreen : Screen("login")

    data object Main : Screen("main") {
        val menu = listOf(
            Loads,
            Stats,
            Notices,
            DriverDetails,
        )

        data object Loads : Screen("main/loads")

        data object Stats : Screen("main/stats")

        data object Notices : Screen("main/notices")

        data object DriverDetails : Screen("main/driver_details")

    }
}