package com.ghostdev.wallpaperstudio.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ghostdev.wallpaperstudio.navigation.graphs.browseGraph
import com.ghostdev.wallpaperstudio.navigation.graphs.favoritesGraph
import com.ghostdev.wallpaperstudio.navigation.graphs.homeGraph
import com.ghostdev.wallpaperstudio.navigation.graphs.settingsGraph
import com.ghostdev.wallpaperstudio.ui.components.NavigationDrawer
import com.ghostdev.wallpaperstudio.ui.components.TopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        NavigationDrawer(
            navController = navController,
            drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                },
                containerColor = Color.White
            ) { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = NavDestinations.HomeGraph,
                    modifier = Modifier
                        .padding(top = paddingValues.calculateTopPadding())
                ) {
                    homeGraph(navController)
                    browseGraph(navController)
                    favoritesGraph(navController)
                    settingsGraph(navController)
                }
            }
        }
    }
}