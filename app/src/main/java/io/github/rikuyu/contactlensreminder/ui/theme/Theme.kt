package io.github.rikuyu.contactlensreminder.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ContactLensReminderTheme(
    darkTheme: Boolean,
    themeColor: ThemeColor,
    content: @Composable () -> Unit,
) {
    val colors = ThemeColor.convertToThemeFromEnum(darkTheme, themeColor)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

enum class ThemeColor {
    Blue, Navy, Cyan, PeacockBlue,
    Green, YellowGreen, Yellow, Orange,
    Brown, Purple, Pink, Red;

    companion object {
        fun convertToThemeFromEnum(isDarkTheme: Boolean, color: ThemeColor): Colors {
            return if (isDarkTheme) {
                when (color) {
                    Blue -> DarkThemePalette.blueDarkColorPalette
                    Navy -> DarkThemePalette.navyDarkColorPalette
                    Cyan -> DarkThemePalette.cyanDarkColorPalette
                    PeacockBlue -> DarkThemePalette.peacockBlueDarkColorPalette
                    Green -> DarkThemePalette.greenDarkColorPalette
                    YellowGreen -> DarkThemePalette.yellowGreenDarkColorPalette
                    Yellow -> DarkThemePalette.yellowDarkColorPalette
                    Orange -> DarkThemePalette.orangeDarkColorPalette
                    Brown -> DarkThemePalette.brownDarkColorPalette
                    Purple -> DarkThemePalette.purpleDarkColorPalette
                    Pink -> DarkThemePalette.pinkDarkColorPalette
                    Red -> DarkThemePalette.redDarkColorPalette
                }
            } else {
                when (color) {
                    Blue -> LightThemePalette.blueLightColorPalette
                    Navy -> LightThemePalette.navyLightColorPalette
                    Cyan -> LightThemePalette.cyanLightColorPalette
                    PeacockBlue -> LightThemePalette.peacockBlueLightColorPalette
                    Green -> LightThemePalette.greenLightColorPalette
                    YellowGreen -> LightThemePalette.yellowGreenLightColorPalette
                    Yellow -> LightThemePalette.yellowLightColorPalette
                    Orange -> LightThemePalette.orangeLightColorPalette
                    Brown -> LightThemePalette.brownLightColorPalette
                    Purple -> LightThemePalette.purpleLightColorPalette
                    Pink -> LightThemePalette.pinkLightColorPalette
                    Red -> LightThemePalette.redLightColorPalette
                }
            }
        }

        fun convertToEnumFromString(color: String): ThemeColor {
            return when (color) {
                Blue.name.lowercase() -> Blue
                Navy.name.lowercase() -> Navy
                Cyan.name.lowercase() -> Cyan
                PeacockBlue.name.lowercase() -> PeacockBlue
                Green.name.lowercase() -> Green
                YellowGreen.name.lowercase() -> YellowGreen
                Yellow.name.lowercase() -> Yellow
                Orange.name.lowercase() -> Orange
                Brown.name.lowercase() -> Brown
                Purple.name.lowercase() -> Purple
                Pink.name.lowercase() -> Pink
                Red.name.lowercase() -> Red
                else -> Blue
            }
        }
    }
}

object LightThemePalette {

    val blueLightColorPalette = lightColors(
        primary = ColorPalette.Blue.primaryBlue,
        primaryVariant = ColorPalette.Blue.primaryVariantBlue,
        secondary = ColorPalette.Blue.secondaryBlue,
        surface = SmoothGray
    )

    val navyLightColorPalette = lightColors(
        primary = ColorPalette.Navy.primaryNavy,
        primaryVariant = ColorPalette.Navy.primaryVariantNavy,
        secondary = ColorPalette.Navy.secondaryNavy,
        surface = SmoothGray
    )

    val cyanLightColorPalette = lightColors(
        primary = ColorPalette.Cyan.primaryCyan,
        primaryVariant = ColorPalette.Cyan.primaryVariantCyan,
        secondary = ColorPalette.Cyan.secondaryCyan,
        surface = SmoothGray
    )

    val peacockBlueLightColorPalette = lightColors(
        primary = ColorPalette.PeacockBlue.primaryPeacockBlue,
        primaryVariant = ColorPalette.PeacockBlue.primaryVariantPeacockBlue,
        secondary = ColorPalette.PeacockBlue.secondaryPeacockBlue,
        surface = SmoothGray
    )

    val greenLightColorPalette = lightColors(
        primary = ColorPalette.Green.primaryGreen,
        primaryVariant = ColorPalette.Green.primaryVariantGreen,
        secondary = ColorPalette.Green.secondaryGreen,
        surface = SmoothGray
    )

    val yellowGreenLightColorPalette = lightColors(
        primary = ColorPalette.YellowGreen.primaryYellowGreen,
        primaryVariant = ColorPalette.YellowGreen.primaryVariantYellowGreen,
        secondary = ColorPalette.YellowGreen.secondaryYellowGreen,
        surface = SmoothGray
    )

    val yellowLightColorPalette = lightColors(
        primary = ColorPalette.Yellow.primaryYellow,
        primaryVariant = ColorPalette.Yellow.primaryVariantYellow,
        secondary = ColorPalette.Yellow.secondaryYellow,
        surface = SmoothGray
    )

    val orangeLightColorPalette = lightColors(
        primary = ColorPalette.Orange.primaryOrange,
        primaryVariant = ColorPalette.Orange.primaryVariantOrange,
        secondary = ColorPalette.Orange.secondaryOrange,
        surface = SmoothGray
    )

    val brownLightColorPalette = lightColors(
        primary = ColorPalette.Brown.primaryBrown,
        primaryVariant = ColorPalette.Brown.primaryVariantBrown,
        secondary = ColorPalette.Brown.secondaryBrown,
        surface = SmoothGray
    )

    val purpleLightColorPalette = lightColors(
        primary = ColorPalette.Purple.primaryPurple,
        primaryVariant = ColorPalette.Purple.primaryVariantPurple,
        secondary = ColorPalette.Purple.secondaryPurple,
        surface = SmoothGray
    )

    val pinkLightColorPalette = lightColors(
        primary = ColorPalette.Pink.primaryPink,
        primaryVariant = ColorPalette.Pink.primaryVariantPink,
        secondary = ColorPalette.Pink.secondaryPink,
        surface = SmoothGray
    )

    val redLightColorPalette = lightColors(
        primary = ColorPalette.Red.primaryRed,
        primaryVariant = ColorPalette.Red.primaryVariantRed,
        secondary = ColorPalette.Red.secondaryRed,
        surface = SmoothGray
    )
}

object DarkThemePalette {
    val blueDarkColorPalette = darkColors(
        primary = ColorPalette.Blue.primaryBlue,
        primaryVariant = ColorPalette.Blue.primaryVariantBlue,
        secondary = ColorPalette.Blue.secondaryBlue,
        surface = Color.Black
    )

    val navyDarkColorPalette = darkColors(
        primary = ColorPalette.Navy.primaryNavy,
        primaryVariant = ColorPalette.Navy.primaryVariantNavy,
        secondary = ColorPalette.Navy.secondaryNavy,
        surface = Color.Black
    )

    val cyanDarkColorPalette = darkColors(
        primary = ColorPalette.Cyan.primaryCyan,
        primaryVariant = ColorPalette.Cyan.primaryVariantCyan,
        secondary = ColorPalette.Cyan.secondaryCyan,
        surface = Color.Black
    )

    val peacockBlueDarkColorPalette = darkColors(
        primary = ColorPalette.PeacockBlue.primaryPeacockBlue,
        primaryVariant = ColorPalette.PeacockBlue.primaryVariantPeacockBlue,
        secondary = ColorPalette.PeacockBlue.secondaryPeacockBlue,
        surface = Color.Black
    )

    val greenDarkColorPalette = darkColors(
        primary = ColorPalette.Green.primaryGreen,
        primaryVariant = ColorPalette.Green.primaryVariantGreen,
        secondary = ColorPalette.Green.secondaryGreen,
        surface = Color.Black
    )

    val yellowGreenDarkColorPalette = darkColors(
        primary = ColorPalette.YellowGreen.primaryYellowGreen,
        primaryVariant = ColorPalette.YellowGreen.primaryVariantYellowGreen,
        secondary = ColorPalette.YellowGreen.secondaryYellowGreen,
        surface = Color.Black
    )

    val yellowDarkColorPalette = darkColors(
        primary = ColorPalette.Yellow.primaryYellow,
        primaryVariant = ColorPalette.Yellow.primaryVariantYellow,
        secondary = ColorPalette.Yellow.secondaryYellow,
        surface = Color.Black
    )

    val orangeDarkColorPalette = darkColors(
        primary = ColorPalette.Orange.primaryOrange,
        primaryVariant = ColorPalette.Orange.primaryVariantOrange,
        secondary = ColorPalette.Orange.secondaryOrange,
        surface = Color.Black
    )

    val brownDarkColorPalette = darkColors(
        primary = ColorPalette.Brown.primaryBrown,
        primaryVariant = ColorPalette.Brown.primaryVariantBrown,
        secondary = ColorPalette.Brown.secondaryBrown,
        surface = Color.Black
    )

    val purpleDarkColorPalette = darkColors(
        primary = ColorPalette.Purple.primaryPurple,
        primaryVariant = ColorPalette.Purple.primaryVariantPurple,
        secondary = ColorPalette.Purple.secondaryPurple,
        surface = Color.Black
    )

    val pinkDarkColorPalette = darkColors(
        primary = ColorPalette.Pink.primaryPink,
        primaryVariant = ColorPalette.Pink.primaryVariantPink,
        secondary = ColorPalette.Pink.secondaryPink,
        surface = Color.Black
    )

    val redDarkColorPalette = darkColors(
        primary = ColorPalette.Red.primaryRed,
        primaryVariant = ColorPalette.Red.primaryVariantRed,
        secondary = ColorPalette.Red.secondaryRed,
        surface = Color.Black
    )
}