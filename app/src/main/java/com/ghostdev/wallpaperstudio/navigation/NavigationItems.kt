package com.ghostdev.wallpaperstudio.navigation

import com.ghostdev.wallpaperstudio.R

data class NavigationItems(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val route: String
)

val navItems = listOf(
    NavigationItems(
        title = "Home",
        selectedIcon = R.drawable.home,
        unselectedIcon = R.drawable.home,
        route = "HomeGraph"
    ),
    NavigationItems(
        title = "Browse",
        selectedIcon = R.drawable.browse,
        unselectedIcon = R.drawable.browse,
        route = "BrowseGraph"
    ),
    NavigationItems(
        title = "Favourites",
        selectedIcon = R.drawable.favourties,
        unselectedIcon = R.drawable.favourties,
        route = "FavoritesGraph"
    ),
    NavigationItems(
        title = "Settings",
        selectedIcon = R.drawable.settings,
        unselectedIcon = R.drawable.settings,
        route = "SettingsGraph"
    )
)