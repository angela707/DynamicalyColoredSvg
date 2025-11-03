package com.example.composeexamples.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeexamples.presentation.examplescreens.ColoredSvgScreen
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen
    ) {
        composable<Routes.HomeScreen> {
            ColoredSvgScreen()
        }
    }
}

sealed class Routes {
    @Serializable
    data object HomeScreen : Routes()

}
