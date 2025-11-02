package com.ghostdev.wallpaperstudio.ui.screens.homepage

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
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
import com.ghostdev.wallpaperstudio.ui.screens.favorites.components.FavoritesCardList
import com.ghostdev.wallpaperstudio.ui.screens.homepage.components.WallpaperPreviewDialog
import com.ghostdev.wallpaperstudio.ui.screens.homepage.components.WallpaperSetupDialog
import com.ghostdev.wallpaperstudio.ui.theme.ClashDisplayFontFamily
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily
import org.koin.compose.koinInject
import kotlin.collections.chunked
import kotlin.collections.forEach


enum class Views {
    LIST_VIEW,
    GRID_VIEW
}

@Composable
fun CategoryScreen(
    navigateBack: () -> Unit
) {
    val prefs = koinInject<ActiveWallpaperPrefs>()
    val activeWallpaperId = prefs.activeWallpaperId.value

    val favorites = rememberSaveable {
        mutableStateListOf(
            Favorite("nature_1", "Nature 1", "Nature", R.drawable.nature_1, false),
            Favorite("nature_2", "Nature 2", "Nature", R.drawable.nature_2),
            Favorite("nature_3", "Nature 3", "Nature", R.drawable.nature_3, false),
            Favorite("nature_4", "Nature 4", "Nature", R.drawable.nature_4),
            Favorite("nature_5", "Nature 5", "Nature", R.drawable.nature_5),
            Favorite("nature_6", "Nature 6", "Nature", R.drawable.nature_6)

        )
    }
    var view by rememberSaveable { mutableStateOf(Views.GRID_VIEW) }
    var selectedFavorite by remember { mutableStateOf<Favorite?>(null) }
    var isWallpaperSetupDialogOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CategoryComponent(
            view = view,
            favorites = favorites,
            toggleView = { selectedView ->
                view = selectedView
            },
            toggleFavorite = { index ->
                favorites[index] = favorites[index].copy(isFavorite = !favorites[index].isFavorite)
            },
            onCardClick = { favorite ->
                selectedFavorite = favorite
            },
            onBackClick = { navigateBack() }
        )
    }

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
private fun CategoryComponent(
    view: Views,
    favorites: List<Favorite>,
    toggleView: (Views) -> Unit,
    toggleFavorite: (Int) -> Unit,
    onCardClick: (Favorite) -> Unit,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
    ) {
        item {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable { onBackClick() },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .size(24.dp),
                    onClick = { onBackClick() },
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.back),
                        contentDescription = "Back"
                    )
                }

                Text(
                    text = "Back to Categories",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF979797)
                    )
                )
            }
        }

        item {
            Spacer(
                modifier = Modifier.size(50.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Nature",
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontFamily = ClashDisplayFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF8F0E1)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            if (view != Views.GRID_VIEW) {
                                toggleView(Views.GRID_VIEW)
                            } else {
                                toggleView(Views.LIST_VIEW)
                            }
                        },
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = if (view == Views.GRID_VIEW)
                                painterResource(R.drawable.grid_view)
                            else
                                painterResource(R.drawable.list_view),
                            tint = Color(0xFFFFA821),
                            contentDescription = "Grid View"
                        )
                    }
                }

            }
        }

        item {
            Spacer(
                modifier = Modifier.size(23.dp)
            )
        }

        item {
            AnimatedContent(
                targetState = view,
                transitionSpec = {
                    val scale = if (targetState == Views.GRID_VIEW) {
                        scaleIn(
                            initialScale = 0.8f,
                            animationSpec = tween(durationMillis = 400)
                        )
                    } else {
                        scaleIn(
                            initialScale = 1.2f,
                            animationSpec = tween(durationMillis = 400)
                        )
                    }

                    val fadeIn = fadeIn(animationSpec = tween(durationMillis = 400))
                    val fadeOut = fadeOut(animationSpec = tween(durationMillis = 400))

                    (scale + fadeIn).togetherWith(
                        scaleOut(
                            targetScale = if (initialState == Views.GRID_VIEW) 1.2f else 0.8f,
                            animationSpec = tween(durationMillis = 400)
                        ) + fadeOut
                    )
                },
                label = "view_transition"
            ) { currentView ->
                Column {
                    if (currentView == Views.GRID_VIEW) {
                        favorites.chunked(2).forEach { rowItems ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                rowItems.forEach { favorite ->
                                    FavoritesCardGrid(
                                        favorite = favorite,
                                        onFavoriteClick = {
                                            toggleFavorite(favorites.indexOf(favorite))
                                        },
                                        onCardClick = {
                                            onCardClick(favorite)
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
                        favorites.forEachIndexed { index, favorite ->
                            FavoritesCardList(
                                modifier = Modifier.fillMaxWidth(),
                                favorite = favorite,
                                onFavoriteClick = {
                                    toggleFavorite(favorites.indexOf(favorite))
                                },
                                onCardClick = {
                                    onCardClick(favorite)
                                }
                            )

                            if (index < favorites.size - 1) {
                                Spacer(modifier = Modifier.size(20.dp))

                                HorizontalDivider(
                                    modifier = Modifier.fillMaxWidth(),
                                    thickness = 1.dp,
                                    color = Color(0xFF0000001A).copy(alpha = 0.1f)
                                )

                                Spacer(modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.size(23.dp))
        }
    }
}