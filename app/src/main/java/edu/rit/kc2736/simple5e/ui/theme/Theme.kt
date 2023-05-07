package edu.rit.kc2736.simple5e.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val LightThemeColors = lightColors(
    primary = primaryLight,
    primaryVariant = primaryLightVariant,
    onPrimary = Black2,
    secondary = lightSecondary,
    secondaryVariant = lightSecondaryVariant,
    onSecondary = Black2,
    error = RedErrorDark,
    onError = RedErrorLight,

    )

private val DarkThemeColors = darkColors(
    primary = primaryDark,
    primaryVariant = primaryDarkVariant,
    onPrimary = White2,
    secondary = darkSecondary,
    secondaryVariant = darkSecondaryVariant,
    onSecondary = White2,
    error = RedErrorLight,
    onError = RedErrorLight,
    //surface = Color(0xFF3c506b),


)


@Composable
fun EZ5eTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
        //DarkThemeColors
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
//    MaterialTheme(
//        //colors = if (darkTheme) DarkThemeColors else LightThemeColors,
//        colors = DarkThemeColors,
//        content= content
//    )
}


@Composable
fun EZ5eDarkTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    val CustomTypography = Typography.copy(
        h4 = MaterialTheme.typography.h4.copy(fontSize = MaterialTheme.typography.h4.fontSize * 1.25),
        h6 = MaterialTheme.typography.h6.copy(fontSize = MaterialTheme.typography.h6.fontSize * 1.25),
        body1 = MaterialTheme.typography.body1.copy(fontSize = MaterialTheme.typography.body1.fontSize * 1.25),
        body2 = MaterialTheme.typography.body2.copy(fontSize = MaterialTheme.typography.body2.fontSize * 1.25),
    )
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = CustomTypography,
        shapes = Shapes,
        content= content
    )
}