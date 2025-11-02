package com.ghostdev.wallpaperstudio.ui.screens.settings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostdev.wallpaperstudio.R
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily

enum class ImageQuality(val displayName: String) {
    HIGH("High ( Best Quality )"),
    MEDIUM("Medium Quality"),
    LOW("Low Quality")
}

@Composable
fun SettingsCard(
    modifier: Modifier = Modifier,
    onSaveSettings: (ImageQuality, Boolean) -> Unit = { _, _ -> },
    onCancel: () -> Unit = {},
    onDisconnect: () -> Unit = {}
) {
    var selectedQuality by remember { mutableStateOf(ImageQuality.HIGH) }
    var notificationEnabled by remember { mutableStateOf(true) }
    var isConnected by remember { mutableStateOf(true) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(24.dp))
            .padding(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Wallpaper Setup",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )

                Text(
                    text = "Configure your wallpaper settings and enable auto-rotation",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        lineHeight = 20.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Image Quality Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(1.dp, Color.Black.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Image Quality",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    )

                    // Dropdown
                    Box {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                                .clickable { isDropdownExpanded = true }
                                .padding(horizontal = 16.dp, vertical = 14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = selectedQuality.displayName,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Gray
                                    )
                                )

                                Icon(
                                    painter = painterResource(R.drawable.arrow_down),
                                    contentDescription = "Dropdown",
                                    modifier = Modifier.size(20.dp),
                                    tint = Color.Black
                                )
                            }
                        }

                        DropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false },
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .background(Color.White)
                        ) {
                            ImageQuality.entries.forEach { quality ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = quality.displayName,
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = PoppinsFontFamily,
                                                fontWeight = FontWeight.Normal,
                                                color = Color.Black
                                            )
                                        )
                                    },
                                    onClick = {
                                        selectedQuality = quality
                                        isDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // Notification Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(1.dp, Color.Black.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Notification",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                )
                            )


                            Switch(
                                checked = notificationEnabled,
                                onCheckedChange = { notificationEnabled = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFFFFA821),
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Color.LightGray
                                )
                            )
                        }

                        Text(
                            text = "Get notified about new wallpapers and updates",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF9C9C9C),
                                lineHeight = 16.sp
                            )
                        )
                    }
                }
            }

            // Cancel Button
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8F8F8),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(21.dp),
                border = BorderStroke(1.dp, Color(0xFFDFDFDF))
            ) {
                Text(
                    text = "Cancel",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            // Save Settings Button
            Button(
                onClick = { onSaveSettings(selectedQuality, notificationEnabled) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA821)
                ),
                shape = RoundedCornerShape(21.dp)
            ) {
                Text(
                    text = "Save Settings",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                )
            }

            // Device Component
            DeviceComponent { deviceModifier ->
                Column(
                    modifier = deviceModifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (isConnected) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(40.dp))
                                .background(Color(0xFFCFFFC3))
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.link),
                                    contentDescription = "Connected",
                                    modifier = Modifier.size(20.dp),
                                    tint = Color(0xFF168200)
                                )

                                Text(
                                    text = "Connected to device",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF168200)
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Click to disconnect",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.clickable {
                                isConnected = false
                                onDisconnect()
                            }
                        )
                    }
                }
            }
        }
    }
}