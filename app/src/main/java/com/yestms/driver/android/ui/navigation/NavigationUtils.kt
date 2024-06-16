package com.yestms.driver.android.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.yestms.driver.android.components.navigation.Screen

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
    route: Screen,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable () -> Unit
) {
    composable(
        route = route.screenName,
        arguments = arguments,
        enterTransition = {
            fadeIn(
                animationSpec = tween(durationMillis = 300)
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(durationMillis = 300)
            )
        },
        popEnterTransition = {
            fadeIn(
                animationSpec = tween(durationMillis = 300)
            )
        },
        popExitTransition = {
            fadeOut(
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {
        content()
    }
}