package com.ghostdev.wallpaperstudio.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ghostdev.wallpaperstudio.navigation.NavDestinations
import com.ghostdev.wallpaperstudio.ui.screens.browse.BrowseScreen

fun NavGraphBuilder.browseGraph(
    navController: NavController) {
    navigation<NavDestinations.BrowseGraph>(
        startDestination = NavDestinations.BrowseGraph.Browse
    ) {
        composable<NavDestinations.BrowseGraph.Browse> {
            BrowseScreen(
                navigateToCategories = {
                    navController.navigate(NavDestinations.HomeGraph.Category)
                }
            )
        }
    }
}