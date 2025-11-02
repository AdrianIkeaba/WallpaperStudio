package com.ghostdev.wallpaperstudio.ui.screens.favorites

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostdev.wallpaperstudio.R
import com.ghostdev.wallpaperstudio.data.models.Favorite
import com.ghostdev.wallpaperstudio.data.prefs.ActiveWallpaperPrefs
import com.ghostdev.wallpaperstudio.ui.screens.favorites.components.FavoritesCardGrid
import com.ghostdev.wallpaperstudio.ui.screens.homepage.components.WallpaperPreviewDialog
import com.ghostdev.wallpaperstudio.ui.screens.homepage.components.WallpaperSetupDialog
import com.ghostdev.wallpaperstudio.ui.theme.ClashDisplayFontFamily
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily
import org.koin.compose.koinInject

@Composable
fun FavoritesScreen(
    navigateToBrowse: () -> Unit
) {
    val prefs = koinInject<ActiveWallpaperPrefs>()
    val activeWallpaperId = prefs.activeWallpaperId.value

    val favorites = rememberSaveable {
        mutableStateListOf(
            Favorite("nature_2", "Nature 2", "Nature", R.drawable.nature_2),
            Favorite("nature_4", "Nature 4", "Nature", R.drawable.nature_4),
            Favorite("nature_5", "Nature 5", "Nature", R.drawable.nature_5),
            Favorite("nature_6", "Nature 6", "Nature", R.drawable.nature_6)
        )
    }
    var selectedFavorite by remember { mutableStateOf<Favorite?>(null) }
    var isWallpaperSetupDialogOpen by remember { mutableStateOf(false) }


    FavoritesComponent(
        favorites = favorites,
        deleteFavorite = { index ->
            favorites.removeAt(index)
        },
        onBrowseClicked = { navigateToBrowse() },
        onCardClicked = { favorite ->
            selectedFavorite = favorite
        }
    )

    AnimatedVisibility(
        visible = selectedFavorite != null,
        enter = fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f),
        exit = fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f),
        modifier = Modifier.fillMaxSize(),
    ) {
        selectedFavorite?.let { favorite ->
            WallpaperPreviewDialog(
                favorite = favorite,
                onDismiss = { selectedFavorite = null },
                onSetWallpaper = {
                    isWallpaperSetupDialogOpen = true
                },
                onToggleFavorite = {
                    val index = favorites.indexOf(favorite)
                    if (index != -1) {
                        favorites[index] =
                            favorites[index].copy(isFavorite = !favorites[index].isFavorite)
                        selectedFavorite = favorites[index]
                    }
                }
            )
        }
    }

    AnimatedVisibility(
        visible = isWallpaperSetupDialogOpen,
        enter = fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f),
        exit = fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f),
        modifier = Modifier.fillMaxSize(),
    ) {
        WallpaperSetupDialog(
            isActivated = (selectedFavorite?.id == activeWallpaperId),
            onDismiss = {
                isWallpaperSetupDialogOpen = false
            },
            onSaveSettings = {
                prefs.setActiveWallpaperId(selectedFavorite!!.id)
                selectedFavorite = null
                isWallpaperSetupDialogOpen = false
            }
        )
    }
}

@Composable
private fun FavoritesComponent(
    favorites: List<Favorite>,
    deleteFavorite: (Int) -> Unit,
    onBrowseClicked: () -> Unit,
    onCardClicked: (Favorite) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {
        // Header
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Saved Wallpapers",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = ClashDisplayFontFamily,
                        fontWeight = FontWeight.Medium,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFBB03B),
                                Color(0xFFEC0C43)
                            )
                        )
                    )
                )

                Text(
                    text = "Your saved wallpapers collection.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF575757)
                    )
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }

        if (favorites.isNotEmpty()) {
            items(favorites.chunked(2).size) { rowIndex ->
                val rowItems = favorites.chunked(2)[rowIndex]

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    rowItems.forEach { favorite ->
                        FavoritesCardGrid(
                            favorite = favorite,
                            onFavoriteClick = {
                                deleteFavorite(favorites.indexOf(favorite))
                            },
                            onCardClick = {
                                onCardClicked(favorite)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        } else {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                    , verticalArrangement = Arrangement.spacedBy(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .aspectRatio(197.57f/ 185.29f),
                        painter = painterResource(R.drawable.no_saved_wallpapers),
                        contentDescription = "No saved wallpapers"
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Saved Wallpapers",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF575757),
                            )
                        )

                        Text(
                            text = "Start saving your favorite wallpapers to see them here",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF575757)
                            )
                        )


                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .aspectRatio(200f/50f),
                            onClick = {
                                onBrowseClicked()
                            },
                            shape = RoundedCornerShape(21.dp),
                            colors = ButtonColors(
                                containerColor = Color(0xFFFBB03B),
                                contentColor = Color.White,
                                disabledContainerColor = Color(0xFFFBB03B).copy(alpha = 0.5f),
                                disabledContentColor = Color.White.copy(alpha = 0.5f)
                            )
                        ) {
                            Text(
                                text = "Browse Wallpapers",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            )
                        }
                    }

                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}