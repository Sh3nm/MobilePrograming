package com.example.gymmembershipapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FitTrackDarkColorScheme = darkColorScheme(
    primary = AccentGreen,
    onPrimary = Color.Black,
    secondary = SecondaryOrange,
    onSecondary = Color.Black,
    background = BackgroundDark,
    onBackground = TextPrimary,
    surface = SurfaceDark,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceElevated,
    onSurfaceVariant = TextSecondary,
    outline = DividerColor,
    error = ErrorRed,
    onError = Color.White
)

@Composable
fun FitTrackTheme(
    content: @Composable () -> Unit
) {
    // Dark theme only — FitTrack visual identity.
    MaterialTheme(
        colorScheme = FitTrackDarkColorScheme,
        typography = FitTrackTypography,
        content = content
    )
}

// Backwards-compatible alias kept so the generated scaffold name still resolves.
@Composable
fun GymMembershipAppTheme(content: @Composable () -> Unit) = FitTrackTheme(content)
