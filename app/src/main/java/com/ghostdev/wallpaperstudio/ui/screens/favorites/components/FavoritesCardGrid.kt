package com.ghostdev.wallpaperstudio.ui.screens.favorites.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostdev.wallpaperstudio.R
import com.ghostdev.wallpaperstudio.data.models.Favorite
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily
import com.kyant.backdrop.backdrops.layerBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.lens

@Composable
fun FavoritesCardGrid(
    favorite: Favorite,
    onFavoriteClick: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imageBackdrop = rememberLayerBackdrop()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.65f)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onCardClick() }
    ) {
        // Background Image
        Image(
            modifier = Modifier
                .fillMaxSize()
                .layerBackdrop(imageBackdrop),
            painter = painterResource(favorite.image),
            contentDescription = "Favorite Image",
            contentScale = ContentScale.Crop
        )

        // Favorite Heart Button
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
        ) {
            if (favorite.isFavorite) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable {
                            onFavoriteClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(R.drawable.saved_heart),
                        tint = Color(0xFFFFA821),
                        contentDescription = "Saved Favorite"
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .drawBackdrop(
                            backdrop = imageBackdrop,
                            shape = { CircleShape },
                            effects = {
                                lens(
                                    refractionHeight = 12f.dp.toPx(),
                                    refractionAmount = 16f.dp.toPx(),
                                    chromaticAberration = true
                                )
                            }
                        )
                        .clickable {
                            onFavoriteClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(R.drawable.heart),
                        tint = Color.White,
                        contentDescription = "Saved Favorite"
                    )
                }
            }

        }

        // Bottom Content
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Title
            Text(
                text = favorite.title,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            )

            Box(
                modifier = Modifier
            ) {
                // Glass Background
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .drawBackdrop(
                            backdrop = imageBackdrop,
                            shape = { CircleShape },
                            effects = {
                                lens(
                                    refractionHeight = 12f.dp.toPx(),
                                    refractionAmount = 16f.dp.toPx(),
                                    chromaticAberration = true
                                )
                            }
                        )
                ) {
                    Text(
                        text = favorite.category,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontFamily = PoppinsFontFamily,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}