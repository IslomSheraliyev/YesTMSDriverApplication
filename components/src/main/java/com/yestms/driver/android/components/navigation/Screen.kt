package com.yestms.driver.android.components.navigation

sealed class Screen(
    val screenName: String
) {
    data object LoginScreen : Screen("login")

    data object Main : Screen("main") {
        val menu = listOf(
            Loads,
            Stats,
            Notifications,
            DriverDetails,
        )

        data object Loads : Screen("main/loads")

        data object Stats : Screen("main/stats")

        data object Notifications : Screen("main/notifications")

        data object DriverDetails : Screen("main/driver_details")

    }
}