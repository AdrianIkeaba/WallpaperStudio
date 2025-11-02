package com.ghostdev.wallpaperstudio.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostdev.wallpaperstudio.ui.screens.settings.components.SettingsCard
import com.ghostdev.wallpaperstudio.ui.theme.ClashDisplayFontFamily
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily

@Composable
fun SettingsScreen() {
    SettingsComponent()
}

@Composable
private fun SettingsComponent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Settings",
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
                    text = "Customize your Wallpaper Studio experience",
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
            Spacer(modifier = Modifier.size(50.dp))
        }

        item {
            SettingsCard(
                onSaveSettings = { quality, notificationEnabled ->
                    println("Quality: $quality, Notifications: $notificationEnabled")
                },
                onCancel = {
                    // Handle cancel
                },
                onDisconnect = {
                    // Handle disconnect
                }
            )
        }

        item {
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}