package com.yestms.driver.android.components.navigation

sealed class Screen(
    val screenName: String
) {

    companion object {
        const val ID = "id"
    }

    data object Main : Screen("main") {
        data object Home : Screen("main/home") {
            val menu = mapOf(
                "Loads" to Loads,
                "Stats" to Stats,
                "Notifications" to Notifications
            )

            data object Loads : Screen("main/home/loads")

            data object Stats : Screen("main/home/stats")

            data object Notifications : Screen("main/home/notifications")
        }

        data object Details : Screen("main/details/{$ID}") {
            fun add(id: Int) = "main/details/$id"
        }
    }
}