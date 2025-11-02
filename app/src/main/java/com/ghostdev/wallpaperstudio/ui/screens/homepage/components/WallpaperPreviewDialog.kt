package com.ghostdev.wallpaperstudio.ui.screens.homepage.components

import android.view.ViewGroup
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.ghostdev.wallpaperstudio.R
import com.ghostdev.wallpaperstudio.data.models.Favorite
import com.ghostdev.wallpaperstudio.ui.screens.settings.components.DeviceComponent
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.delay

@Composable
fun WallpaperPreviewDialog(
    modifier: Modifier = Modifier,
    favorite: Favorite,
    onDismiss: () -> Unit,
    onSetWallpaper: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    var showLinkCopied by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        val dialogWindowProvider = LocalView.current.parent as? DialogWindowProvider
        LaunchedEffect(dialogWindowProvider) {
            dialogWindowProvider?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onDismiss
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Link Copied Toast
                    androidx.compose.animation.AnimatedVisibility(
                        visible = showLinkCopied,
                        enter = fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f),
                        exit = fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f)
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(8.dp, RoundedCornerShape(24.dp))
                                .clip(RoundedCornerShape(24.dp))
                                .background(Color.White)
                                .padding(horizontal = 20.dp, vertical = 12.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.link),
                                    contentDescription = "Link",
                                    tint = Color(0xFF8F5700),
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "Link Copied",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF8F5700)
                                    )
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Main Dialog Content
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { }
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 24.dp)
                            .padding(top = 70.dp, bottom = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Device Preview
                        DeviceComponent { deviceModifier ->
                            Image(
                                painter = painterResource(favorite.image),
                                contentDescription = "Wallpaper Preview",
                                modifier = deviceModifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(36.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Preview Title
                        Text(
                            text = "Preview",
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            ),
                            modifier = Modifier.align(Alignment.Start)
                        )

                        Spacer(modifier = Modifier.height(37.dp))

                        // Name Section
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Name",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF808080)
                                )
                            )
                            Text(
                                text = favorite.title,
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Tags Section
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Tags",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF808080)
                                )
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Nature", "Ambience", "Flowers").forEach { tag ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(20.dp))
                                            .background(Color(0x33BFBFBF))
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = tag,
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = PoppinsFontFamily,
                                                fontWeight = FontWeight.Normal,
                                                color = Color.Black
                                            )
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Description Section
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Description",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF808080)
                                )
                            )
                            Text(
                                text = "Discover the pure beauty of \"Natural Essence\" – your gateway to authentic, nature-inspired experiences. Let this unique collection elevate your senses and connect you with the unrefined ele...",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Black,
                                            Color.White
                                        )
                                    )
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(37.dp))

                        // Action Buttons Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Share Button
                            IconButton(
                                onClick = {
                                    showLinkCopied = true
                                },
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(0x33BFBFBF))
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.share),
                                    contentDescription = "Share",
                                    tint = Color(0xFF808080),
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            // Minimize Button
                            IconButton(
                                onClick = { /* Handle minimize */ },
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(0x33BFBFBF))
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.minimize),
                                    contentDescription = "Minimize",
                                    tint = Color(0xFF808080),
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            // Settings Button
                            IconButton(
                                onClick = { /* Handle settings */ },
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(0x33BFBFBF))
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.settings),
                                    contentDescription = "Settings",
                                    tint = Color(0xFF808080),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedButton(
                            onClick = onToggleFavorite,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF8F8F8)
                            ),
                            shape = RoundedCornerShape(21.dp),
                            border = BorderStroke(width = 1.dp, Color(0xFFDFDFDF))
                        ) {
                            Icon(
                                painter = painterResource(
                                    if (favorite.isFavorite) R.drawable.saved_heart else R.drawable.heart
                                ),
                                contentDescription = "Favorite",
                                tint = Color.Black,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Save to Favorites",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Set to Wallpaper Button
                        Button(
                            onClick = onSetWallpaper,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFA821)
                            ),
                            shape = RoundedCornerShape(21.dp)
                        ) {
                            Text(
                                text = "Set to Wallpaper",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            )
                        }
                    }

                    // Close button - positioned on top
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                    ) {
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFBB03B).copy(alpha = 0.1f))
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = "Close",
                                tint = Color(0xFFFBB03B),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    // Auto-hide Link Copied toast after 3 seconds
    LaunchedEffect(showLinkCopied) {
        if (showLinkCopied) {
            delay(3000)
            showLinkCopied = false
        }
    }
}