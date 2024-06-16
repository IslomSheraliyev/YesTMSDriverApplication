package com.yestms.driver.android.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

val NavController.canGoBack: Boolean
    get() =
        this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED


fun NavController.safeNavigate(screen: String) {
    if (canGoBack)
        this.navigate(screen)
}

fun NavController.safeNavigate(screen: String, navOptions: NavOptions) {
    if (canGoBack)
        this.navigate(screen, navOptions)
}

fun NavController.safePopBackStack() {
    if (canGoBack)
        this.popBackStack()
}

fun NavGraphBuilder.createScreen(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable () -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = 300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(durationMillis = 300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = 300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {
        content()
    }
}