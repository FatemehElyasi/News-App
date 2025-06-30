package ir.fatemelyasi.news.view.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val error: Color,
    val onError: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val onTertiaryContainer: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val outline: Color,
    val onSurfaceVariant: Color
)

val LightColorScheme = CustomColors(
    primary = Blue40,
    onPrimary = Black,
    primaryContainer = Blue90,
    onPrimaryContainer = Blue10,
    secondary = DarkBlue40,
    onSecondary = White,
    secondaryContainer = DarkBlue90,
    onSecondaryContainer = DarkBlue10,
    onTertiaryContainer = Yellow10,
    error = Red40,
    onError = White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = Grey99,
    onSurface = Grey10,
    outline = BlueGrey50,
    onSurfaceVariant = BlueGrey30,
    )

val DarkColorScheme = CustomColors(
    primary = Blue80,
    onPrimary = White,
    primaryContainer = Grey10,
    onPrimaryContainer = Blue90,
    secondary = DarkBlue80,
    onSecondary = DarkBlue20,
    secondaryContainer = DarkBlue30,
    onSecondaryContainer = DarkBlue90,
    onTertiaryContainer = Yellow90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Grey90,
    surface = Grey10,
    onSurface = Grey80,
    outline = BlueGrey60,
    onSurfaceVariant = BlueGrey80,
)


val LocalCustomColors = staticCompositionLocalOf<CustomColors> {
    LightColorScheme
}
val LocalCustomTypography = staticCompositionLocalOf<AppTypography> {
    ComposeTypography
}

@Composable
fun ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalCustomTypography provides ComposeTypography
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            content()
        }
    }
}

