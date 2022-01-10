package com.example.contactlensreminder.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = CleanBlue,
    primaryVariant = LightBlue,
    secondary = LightBlue
)

private val LightColorPalette = lightColors(
    primary = CleanBlue,
    primaryVariant = LightBlue,
    secondary = LightBlue
)

@Composable
fun ContactLensReminderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}