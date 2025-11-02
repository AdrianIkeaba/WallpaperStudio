package com.ghostdev.wallpaperstudio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ghostdev.wallpaperstudio.navigation.NavDestinations
import com.ghostdev.wallpaperstudio.navigation.navItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(
    navController: NavController,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    ModalDrawerSheet(
                        drawerContainerColor = Color.White
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        navItems.forEachIndexed { index, item ->
                            val isSelected = when (index) {
                                0 -> currentRoute?.contains("HomeGraph") == true
                                1 -> currentRoute?.contains("BrowseGraph") == true
                                2 -> currentRoute?.contains("FavoritesGraph") == true
                                3 -> currentRoute?.contains("SettingsGraph") == true
                                else -> false
                            }

                            NavigationDrawerItem(
                                label = { Text(text = item.title) },
                                selected = isSelected,
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    when (index) {
                                        0 -> navController.navigate(NavDestinations.HomeGraph) {
                                            popUpTo(NavDestinations.HomeGraph) {
                                                inclusive = false
                                            }
                                            launchSingleTop = true
                                        }
                                        1 -> navController.navigate(NavDestinations.BrowseGraph) {
                                            popUpTo(NavDestinations.HomeGraph) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                        2 -> navController.navigate(NavDestinations.FavoritesGraph) {
                                            popUpTo(NavDestinations.HomeGraph) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                        3 -> navController.navigate(NavDestinations.SettingsGraph) {
                                            popUpTo(NavDestinations.HomeGraph) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                },
                                icon = {
                                    Icon(
                                        painter = if (isSelected) {
                                            painterResource(item.selectedIcon)
                                        } else {
                                            painterResource(item.unselectedIcon)
                                        },
                                        contentDescription = item.title
                                    )
                                },
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = Color.LightGray.copy(alpha = 0.2f),
                                    unselectedContainerColor = Color.Transparent,
                                    selectedIconColor = Color.Black,
                                    unselectedIconColor = Color.Black,
                                    selectedTextColor = Color.Black,
                                    unselectedTextColor = Color.Black
                                ),
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                            if (index < navItems.size - 1) {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    thickness = 1.dp,
                                    color = Color.LightGray.copy(alpha = 0.5f)
                                )
                            }
                        }
                    }
                }
            },
            gesturesEnabled = true
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                content()
            }
        }
    }
}