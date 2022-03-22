package io.github.rikuyu.contactlensreminder.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = CleanBlue,
    primaryVariant = CleanBlue,
    secondary = LightBlue,
    surface = Color.Black
)

private val LightColorPalette = lightColors(
    primary = CleanBlue,
    primaryVariant = CleanBlue,
    secondary = CleanBlue,
    surface = SmoothGray
)

@Composable
fun ContactLensReminderTheme(
    darkTheme: Boolean,
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