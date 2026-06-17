package com.example.gymmembershipapp.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymmembershipapp.ui.navigation.Routes
import com.example.gymmembershipapp.ui.theme.AccentGreen
import com.example.gymmembershipapp.ui.theme.BackgroundDark
import com.example.gymmembershipapp.ui.theme.SurfaceElevated
import com.example.gymmembershipapp.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000L)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(AccentGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.FitnessCenter,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(Modifier.height(28.dp))

            Text(
                text = "FITTRACK",
                style = MaterialTheme.typography.displayLarge,
                color = Color.White
            )
            Text(
                text = "MEMBERSHIP TRACKER",
                color = TextSecondary,
                fontSize = 12.sp,
                letterSpacing = 2.sp
            )

            Spacer(Modifier.height(36.dp))

            LinearProgressIndicator(
                modifier = Modifier
                    .width(160.dp)
                    .height(4.dp)
                    .clip(CircleShape),
                color = AccentGreen,
                trackColor = SurfaceElevated
            )
        }
    }
}
