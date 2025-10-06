package com.example.composeexamples.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeexamples.presentation.HomeScreen
import com.example.composeexamples.presentation.examplescreens.LazyLayoutsKeysExampleScreen
import com.example.composeexamples.presentation.examplescreens.RelativeSizesExampleScreen
import com.example.composeexamples.presentation.examplescreens.SlottingExampleScreen
import com.example.composeexamples.presentation.examplescreens.UiStateExampleScreen
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
            HomeScreen(
                onNavigateToSlotting = { navController.navigate(Routes.SlottingExample) },
                onNavigateToUiState = { navController.navigate(Routes.UiStateExample) },
                onNavigateToLazyLayouts = { navController.navigate(Routes.LazyLayoutsKeysExample) },
                onNavigateToRelativeSizes = { navController.navigate(Routes.RelativeSizesExample) }
            )
        }

        composable<Routes.SlottingExample> {
            SlottingExampleScreen()
        }

        composable<Routes.UiStateExample> {
            UiStateExampleScreen()
        }

        composable<Routes.LazyLayoutsKeysExample> {
            LazyLayoutsKeysExampleScreen()
        }

        composable<Routes.RelativeSizesExample> {
            RelativeSizesExampleScreen()
        }
    }
}

sealed class Routes {
    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data object SlottingExample : Routes()

    @Serializable
    data object UiStateExample : Routes()

    @Serializable
    data object LazyLayoutsKeysExample : Routes()

    @Serializable
    data object RelativeSizesExample : Routes()
}
