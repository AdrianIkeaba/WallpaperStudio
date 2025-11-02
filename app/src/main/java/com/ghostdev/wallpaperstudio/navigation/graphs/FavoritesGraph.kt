package com.ghostdev.wallpaperstudio.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ghostdev.wallpaperstudio.navigation.NavDestinations
import com.ghostdev.wallpaperstudio.ui.screens.favorites.FavoritesScreen

fun NavGraphBuilder.favoritesGraph(navController: NavController) {
    navigation<NavDestinations.FavoritesGraph>(
        startDestination = NavDestinations.FavoritesGraph.Favorites
    ) {
        composable<NavDestinations.FavoritesGraph.Favorites> {
            FavoritesScreen(
                navigateToBrowse = {
                    navController.navigate(NavDestinations.BrowseGraph.Browse) {
                        popUpTo(NavDestinations.BrowseGraph.Browse) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}