package com.ghostdev.wallpaperstudio.ui.screens.homepage.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostdev.wallpaperstudio.R
import com.ghostdev.wallpaperstudio.ui.theme.ClashDisplayFontFamily
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily

@Composable
fun ActiveWallpaperComponent(
    wallpaperImage: Int,
    category: String,
    selection: String,
    onShareClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Wallpaper Image
            Image(
                painter = painterResource(id = wallpaperImage),
                contentDescription = "Active Wallpaper",
                modifier = Modifier
                    .width(117.77.dp)
                    .aspectRatio(117.77f/ 210.33f)
                    .border(3.dp, Color(0xFFE3E3E3), RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop
            )

            // Content Section
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Title and Description
                Column {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFFFFA726),
                                            Color(0xFFEF5350)
                                        )
                                    ),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = ClashDisplayFontFamily
                                )
                            ) {
                                append("Your Active Wallpaper")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "This wallpaper is currently set as your active background",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF808080),
                        fontFamily = PoppinsFontFamily,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Category
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xFF808080),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = PoppinsFontFamily
                                )
                            ) {
                                append("Category - ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = PoppinsFontFamily
                                )
                            ) {
                                append(category)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Selection
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xFF808080),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = PoppinsFontFamily
                                )
                            ) {
                                append("Selection - ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = PoppinsFontFamily
                                )
                            ) {
                                append(selection)
                            }
                        }
                    )
                }

                // Action Buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Share Button
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(11.dp))
                            .background(Color(0xFFF5F5F5))
                            .border(0.5.dp, Color(0xFFE5E5E5), RoundedCornerShape(11.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = onShareClick) {
                            Icon(
                                painter = painterResource(id = R.drawable.share),
                                contentDescription = "Share",
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // Settings Button
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(0.5.dp, Color(0xFFE5E5E5), RoundedCornerShape(11.dp))
                            .clip(RoundedCornerShape(11.dp))
                            .background(Color(0xFFF5F5F5)),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = onSettingsClick) {
                            Icon(
                                painter = painterResource(id = R.drawable.settings),
                                contentDescription = "Settings",
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}