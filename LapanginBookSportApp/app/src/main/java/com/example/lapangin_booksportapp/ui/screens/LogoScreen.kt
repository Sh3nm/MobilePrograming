package com.example.lapangin_booksportapp.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lapangin_booksportapp.ui.theme.Dark
import com.example.lapangin_booksportapp.ui.theme.Lime
import com.example.lapangin_booksportapp.ui.theme.LimeSubtle
import com.example.lapangin_booksportapp.ui.theme.Syne
import com.example.lapangin_booksportapp.ui.theme.DMSans
import com.example.lapangin_booksportapp.ui.theme.TextMuted
import com.example.lapangin_booksportapp.ui.theme.TextMuted2
import com.example.lapangin_booksportapp.ui.theme.TextPrimary
import kotlinx.coroutines.launch

@Composable
fun LogoScreen(
    onGetStarted: () -> Unit,
    onLoginClick: () -> Unit
) {
    val contentAlpha = remember { Animatable(0f) }
    val contentOffset = remember { Animatable(30f) }

    LaunchedEffect(Unit) {
        launch {
            contentAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
            )
        }
        launch {
            contentOffset.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
    ) {
        // Background decorative sport emojis (faded)
        Text(
            text = "⚽",
            fontSize = 80.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 20.dp, y = 80.dp)
                .alpha(0.06f)
        )
        Text(
            text = "🏸",
            fontSize = 60.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = (-15).dp, y = (-60).dp)
                .alpha(0.05f)
        )
        Text(
            text = "🏀",
            fontSize = 70.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 30.dp, y = (-140).dp)
                .alpha(0.04f)
        )
        Text(
            text = "🎾",
            fontSize = 50.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 40.dp, y = 160.dp)
                .alpha(0.04f)
        )

        // Radial glow
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-80).dp)
                .size(280.dp)
                .alpha(0.3f)
                .blur(100.dp)
                .background(
                    color = LimeSubtle,
                    shape = CircleShape
                )
        )

        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .alpha(contentAlpha.value)
                .offset(y = contentOffset.value.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Sport icon
            Text(
                text = "⚽",
                fontSize = 64.sp,
            )

            Spacer(modifier = Modifier.height(20.dp))

            // App name
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = TextPrimary,
                            fontFamily = Syne,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 44.sp,
                        )
                    ) {
                        append("Book")
                    }
                    withStyle(
                        SpanStyle(
                            color = Lime,
                            fontFamily = Syne,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 44.sp,
                        )
                    ) {
                        append("Sport")
                    }
                },
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Tagline
            Text(
                text = "Booking lapangan olahraga\njadi lebih mudah",
                fontFamily = DMSans,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Get Started button
            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Lime,
                    contentColor = Dark
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Text(
                    text = "Mulai Sekarang",
                    fontFamily = Syne,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    letterSpacing = (-0.01).sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Login link
            Row {
                Text(
                    text = "Sudah punya akun? ",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = TextMuted2
                )
                Text(
                    text = "Masuk",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Lime,
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onLoginClick() }
                )
            }
        }
    }
}
