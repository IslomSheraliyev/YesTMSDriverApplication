package com.yestms.driver.android.components.navigation

sealed class Screen(
    val screenName: String
) {

    companion object {
        const val ID = "id"
    }

    data object Main : Screen("main") {
        val menu = mapOf(
            "Loads" to Loads,
            "Stats" to Stats,
            "Notifications" to Notifications
        )

        data object Loads : Screen("main/loads")

        data object Stats : Screen("main/stats")

        data object Notifications : Screen("main/notifications")

        data object Details : Screen("main/details/{$ID}") {
            fun add(id: Int) = "main/details/$id"
        }
    }
}