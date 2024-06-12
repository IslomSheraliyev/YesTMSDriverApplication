package com.yestms.driver.android.components.navigation

sealed class Screen(
    val screenName: String
) {
    data object SplashScreen : Screen("splash")
}