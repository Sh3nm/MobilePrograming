package com.example.lapangin_booksportapp.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lapangin_booksportapp.ui.theme.Dark
import com.example.lapangin_booksportapp.ui.theme.Lime
import com.example.lapangin_booksportapp.ui.theme.LimeSubtle
import com.example.lapangin_booksportapp.ui.theme.Syne
import com.example.lapangin_booksportapp.ui.theme.TextMuted
import com.example.lapangin_booksportapp.ui.theme.TextPrimary
import com.example.lapangin_booksportapp.ui.theme.DMSans
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    val alphaAnim = remember { Animatable(0f) }
    val scaleAnim = remember { Animatable(0.8f) }
    val glowAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Start animations in parallel
        launch {
            alphaAnim.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
            )
        }
        launch {
            scaleAnim.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
            )
        }
        launch {
            glowAlpha.animateTo(
                targetValue = 0.6f,
                animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
            )
        }

        // Wait then navigate
        delay(2500L)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark),
        contentAlignment = Alignment.Center
    ) {
        // Radial lime glow behind logo
        Box(
            modifier = Modifier
                .size(200.dp)
                .alpha(glowAlpha.value)
                .blur(80.dp)
                .background(
                    color = LimeSubtle,
                    shape = CircleShape
                )
        )

        // Logo content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .alpha(alphaAnim.value)
                .scale(scaleAnim.value)
        ) {
            // Sport emoji icon
            Text(
                text = "⚽",
                fontSize = 56.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // App name: Book + Sport
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = TextPrimary,
                            fontFamily = Syne,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 36.sp,
                            letterSpacing = (-0.5).sp
                        )
                    ) {
                        append("Book")
                    }
                    withStyle(
                        SpanStyle(
                            color = Lime,
                            fontFamily = Syne,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 36.sp,
                            letterSpacing = (-0.5).sp
                        )
                    ) {
                        append("Sport")
                    }
                },
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tagline
            Text(
                text = "Booking Lapangan, Tanpa Ribet.",
                fontFamily = DMSans,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                color = TextMuted,
                letterSpacing = 0.3.sp
            )
        }
    }
}
