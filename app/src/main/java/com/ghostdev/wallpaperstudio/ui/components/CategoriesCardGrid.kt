package com.ghostdev.wallpaperstudio.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostdev.wallpaperstudio.data.models.Category
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily
import com.kyant.backdrop.backdrops.layerBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.lens

@Composable
fun CategoriesCardGrid(
    category: Category,
    modifier: Modifier = Modifier,
    onCategoryClicked: () -> Unit
) {
    val imageBackdrop = rememberLayerBackdrop()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(400f/297f)
            .clip(RoundedCornerShape(26.dp)),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        onClick = {
            onCategoryClicked()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = painterResource(id = category.image),
                contentDescription = category.title,
                modifier = Modifier
                    .fillMaxSize()
                    .layerBackdrop(imageBackdrop),
                contentScale = ContentScale.Crop
            )

            // Content
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Title
                Text(
                    text = category.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontFamily = PoppinsFontFamily
                )

                // Subtitle
                Text(
                    text = category.subTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    fontFamily = PoppinsFontFamily,
                )
                
                Box(
                    modifier = Modifier
                ) {
                    // Glass Background
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                Color.White.copy(alpha = 0.25f)
                            )
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
                            text = "${category.number} wallpapers",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            fontFamily = PoppinsFontFamily,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}