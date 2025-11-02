package com.ghostdev.wallpaperstudio.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ghostdev.wallpaperstudio.navigation.NavDestinations
import com.ghostdev.wallpaperstudio.ui.screens.homepage.CategoryScreen
import com.ghostdev.wallpaperstudio.ui.screens.homepage.HomeScreen

fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation<NavDestinations.HomeGraph>(
        startDestination = NavDestinations.HomeGraph.Home
    ) {
        composable<NavDestinations.HomeGraph.Home> {
            HomeScreen(
                navigateToCategories = {
                    navController.navigate(NavDestinations.HomeGraph.Category)
                }
            )
        }

        composable<NavDestinations.HomeGraph.Category> {
            CategoryScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}