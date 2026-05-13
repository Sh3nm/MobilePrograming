package com.example.lapangin_booksportapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BookSportColorScheme = darkColorScheme(
    primary = Lime,
    onPrimary = Dark,
    primaryContainer = LimeSubtle,
    onPrimaryContainer = Lime,

    secondary = LimeDim,
    onSecondary = Dark,

    background = Dark,
    onBackground = TextPrimary,

    surface = Surface,
    onSurface = TextPrimary,
    surfaceVariant = Surface2,
    onSurfaceVariant = TextMuted,

    outline = Border,
    outlineVariant = Surface3,

    error = Red,
    onError = Color.White,
)

@Composable
fun LapanginBookSportAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = BookSportColorScheme,
        typography = Typography,
        content = content
    )
}