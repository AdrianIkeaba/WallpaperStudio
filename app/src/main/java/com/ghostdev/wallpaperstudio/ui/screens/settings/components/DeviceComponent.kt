package com.ghostdev.wallpaperstudio.ui.screens.settings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ghostdev.wallpaperstudio.R

@Composable
fun DeviceComponent(
    content: @Composable (modifier: Modifier) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Device Frame
        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(258.05f / 524.99f),
            painter = painterResource(R.drawable.device_frame2),
            contentDescription = "Device Frame",
            contentScale = ContentScale.Fit
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .aspectRatio(248.51f / 525.03f)
                .align(Alignment.Center)
        ) {
            content(
                Modifier
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(258.05f / 524.99f)
                .align(Alignment.Center)
        ) {
            DynamicIsland(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 20.dp)
            )
        }

        // Bottom indicator
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(258.05f / 524.99f)
                .align(Alignment.Center)
                .offset(y = (-20).dp)
        ) {
            Box(
                modifier = Modifier
                    .width(85.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0xFFD9D9D9))
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
private fun DynamicIsland(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(75.dp)
            .height(25.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Black)
            .padding(5.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Color(0xFF2A2A2A))
        )
    }
}