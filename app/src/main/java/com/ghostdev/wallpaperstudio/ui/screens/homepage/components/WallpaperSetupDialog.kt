package com.ghostdev.wallpaperstudio.ui.screens.homepage.components

import android.view.ViewGroup
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.ghostdev.wallpaperstudio.R
import com.ghostdev.wallpaperstudio.data.prefs.ActiveWallpaperPrefs
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily

enum class DisplayMode {
    FIT, FILL, STRETCH, TILE
}

@Composable
fun WallpaperSetupDialog(
    isActivated: Boolean,
    onDismiss: () -> Unit,
    onSaveSettings: () -> Unit
) {
    var selectedDisplayMode by remember { mutableStateOf(DisplayMode.FIT) }
    var autoRotation by remember { mutableStateOf(true) }
    var lockWallpaper by remember { mutableStateOf(true) }
    var syncAcrossDevices by remember { mutableStateOf(false) }
    val switchON = remember {
        mutableStateOf(true)
    }

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
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onDismiss
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 20.dp)
                    .statusBarsPadding()
                    .padding(top = 48.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
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
                        .padding(24.dp)
                ) {
                    // Title
                    Text(
                        text = "Wallpaper Setup",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Configure your wallpaper settings and enable auto-rotation",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF9E9E9E)
                        )
                    )

                    Spacer(modifier = Modifier.height(26.dp))

                    // Activate Wallpaper Section
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .padding(20.dp)
                    ) {
                        Column {
                            Text(
                                text = "Activate Wallpaper",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Set the selected wallpaper as your desktop background",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF9C9C9C)
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(24.dp))
                                    .background(
                                        if (isActivated) Color(0xFFC8FFBD)
                                    else Color(0xFF7C7C7C).copy(alpha = 0.1f)
                                    )
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (isActivated) {
                                        Icon(
                                            painter = painterResource(R.drawable.check),
                                            contentDescription = "Check",
                                            tint = Color(0xFF4CAF50),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    } else {
                                        Icon(
                                            painter = painterResource(R.drawable.close),
                                            contentDescription = "Not activated",
                                            tint = Color(0xFF808080),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Text(
                                        text = if (isActivated) "Activated" else "Not Activated",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = PoppinsFontFamily,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (isActivated)
                                                Color(0xFF1BA400)
                                            else
                                                Color(0xFF808080)
                                        )
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(26.dp))

                    // Display mode
                    Text(
                        text = "Display mode",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(16.dp))
                            .background(Color.White)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            DisplayModeOption(
                                title = "Fit",
                                description = "Scale to fit without cropping",
                                isSelected = selectedDisplayMode == DisplayMode.FIT,
                                onClick = { selectedDisplayMode = DisplayMode.FIT }
                            )
                            DisplayModeOption(
                                title = "Fill",
                                description = "Scale to fill the entire screen",
                                isSelected = selectedDisplayMode == DisplayMode.FILL,
                                onClick = { selectedDisplayMode = DisplayMode.FILL }
                            )
                            DisplayModeOption(
                                title = "Stretch",
                                description = "Stretch to fill the screen",
                                isSelected = selectedDisplayMode == DisplayMode.STRETCH,
                                onClick = { selectedDisplayMode = DisplayMode.STRETCH }
                            )
                            DisplayModeOption(
                                title = "Tile",
                                description = "Repeat the image to fill the screen",
                                isSelected = selectedDisplayMode == DisplayMode.TILE,
                                onClick = { selectedDisplayMode = DisplayMode.TILE },
                                isLast = true
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(26.dp))

                    // Auto-Rotation
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .clickable { autoRotation = !autoRotation }
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Auto - Rotation",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Black
                                    )
                                )

                                Switch(
                                    toggleSwitch = {
                                        switchON.value = !switchON.value
                                    },
                                    switchOn = switchON.value
                                )
                            }

                            Text(
                                text = "Automatically change your wallpaper at\nregular intervals",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFFB0B0B0),
                                    textAlign = TextAlign.Start
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(26.dp))

                    // Advanced Settings
                    Text(
                        text = "Advanced Settings",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SettingOption(
                            title = "Lock Wallpaper",
                            description = "Prevent accidental changes",
                            isChecked = lockWallpaper,
                            onCheckedChange = { lockWallpaper = it }
                        )

                        SettingOption(
                            title = "Sync Across Devices",
                            description = "Keep wallpaper consistent on all devices",
                            isChecked = syncAcrossDevices,
                            onCheckedChange = { syncAcrossDevices = it },
                            isLast = true
                        )
                    }

                    Spacer(modifier = Modifier.height(26.dp))

                    // Save Settings Button
                    Button(
                        onClick = onSaveSettings,
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
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Cancel Button
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(21.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5E5E5))
                    ) {
                        Text(
                            text = "Cancel",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DisplayModeOption(
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    isLast: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color(0xFFFFA821) else Color(0xFFE0E0E0),
                        shape = CircleShape
                    )
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFA821))
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFB0B0B0)
                    )
                )
            }
        }
    }
}

@Composable
private fun SettingOption(
    title: String,
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isLast: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!isChecked) }
            .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = if (isChecked) Color(0xFFFFA821) else Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isChecked) {
                    Box(
                        modifier = Modifier
                            .size(15.13.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFFBB03B))
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFB0B0B0)
                    )
                )
            }
        }
    }
}

@Composable
fun Switch(
    scale: Float = 1f,
    width: Dp = 36.dp,
    height: Dp = 20.dp,
    strokeWidth: Dp = 2.dp,
    checkedTrackColor: Color = Color(0xFFFBB03B),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    gapBetweenThumbAndTrackEdge: Dp = 2.dp,
    toggleSwitch: (Boolean) -> Unit,
    switchOn: Boolean
) {
    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    val animatePosition = animateFloatAsState(
        targetValue = if (switchOn)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        toggleSwitch(!switchOn)
                    }
                )
            }
            .clip(RoundedCornerShape(10.dp))
            .background(if (switchOn) checkedTrackColor else Color.White)
    ) {
        // Track
        drawRoundRect(
            color = if (switchOn) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = strokeWidth.toPx())
        )

        // Thumb
        drawCircle(
            color = if (switchOn) Color.White else uncheckedTrackColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }
}