package io.github.rikuyu.contactlensreminder.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ContactLensReminderTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        ThemePalette.BlueDarkColorPalette
    } else {
        ThemePalette.BlueLightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

object ThemePalette {
    val BlueDarkColorPalette = darkColors(
        primary = ThemeColorPalette.Blue.primaryBlue,
        primaryVariant = ThemeColorPalette.Blue.primaryVariantBlue,
        secondary = ThemeColorPalette.Blue.secondaryBlue,
        surface = Color.Black
    )

    val BlueLightColorPalette = lightColors(
        primary = ThemeColorPalette.Blue.primaryBlue,
        primaryVariant = ThemeColorPalette.Blue.primaryVariantBlue,
        secondary = ThemeColorPalette.Blue.secondaryBlue,
        surface = SmoothGray
    )
}