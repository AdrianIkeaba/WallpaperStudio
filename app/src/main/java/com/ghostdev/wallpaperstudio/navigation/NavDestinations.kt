package com.ghostdev.wallpaperstudio.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestinations {

    @Serializable
    data object HomeGraph {

        @Serializable
        data object Home

        @Serializable
        data object Category

    }

    @Serializable
    data object BrowseGraph {

        @Serializable
        data object Browse

    }

    @Serializable
    data object FavoritesGraph {

        @Serializable
        data object Favorites

    }

    @Serializable
    data object SettingsGraph {

        @Serializable
        data object Settings


    }

}