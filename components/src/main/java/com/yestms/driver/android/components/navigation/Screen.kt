package com.yestms.driver.android.components.navigation

sealed class Screen(
    val screenName: String
) {
    data object LoginScreen : Screen("login")

    data object HomeScreen : Screen("home")
}