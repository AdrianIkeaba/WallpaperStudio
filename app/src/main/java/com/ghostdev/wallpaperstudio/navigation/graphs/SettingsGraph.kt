package com.ghostdev.wallpaperstudio.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ghostdev.wallpaperstudio.navigation.NavDestinations
import com.ghostdev.wallpaperstudio.ui.screens.settings.SettingsScreen

fun NavGraphBuilder.settingsGraph(navController: NavController) {
    navigation<NavDestinations.SettingsGraph>(
        startDestination = NavDestinations.SettingsGraph.Settings
    ) {
        composable<NavDestinations.SettingsGraph.Settings> {
            SettingsScreen()
        }
    }
}