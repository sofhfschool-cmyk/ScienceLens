package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val ScienceLensLightScheme = lightColorScheme(
    primary = ScienceBlue,
    onPrimary = PureWhite,
    primaryContainer = ScienceBlueLight,
    onPrimaryContainer = DeepNavy,
    secondary = OpticTeal,
    onSecondary = PureWhite,
    secondaryContainer = OpticTealLight,
    onSecondaryContainer = DeepNavy,
    tertiary = DeepNavy,
    onTertiary = PureWhite,
    tertiaryContainer = LightGreySurface,
    onTertiaryContainer = DeepNavy,
    background = OffWhiteSurface,
    onBackground = TextPrimaryNavy,
    surface = PureWhite,
    onSurface = TextPrimaryNavy,
    surfaceVariant = LightGreySurface,
    onSurfaceVariant = TextSecondarySlate,
    outline = BorderSlate,
    outlineVariant = Color(0xFFCBD5E1)
)

private val ScienceLensDarkScheme = darkColorScheme(
    primary = ScienceBlueBright,
    onPrimary = DeepNavyDark,
    primaryContainer = Color(0xFF1E3A8A),
    onPrimaryContainer = ScienceBlueLight,
    secondary = OpticTealBright,
    onSecondary = DeepNavyDark,
    secondaryContainer = Color(0xFF115E59),
    onSecondaryContainer = OpticTealLight,
    tertiary = ScienceBlueLight,
    onTertiary = DeepNavyDark,
    background = DeepNavyDark,
    onBackground = Color(0xFFF8FAFC),
    surface = Color(0xFF131F33),
    onSurface = Color(0xFFF8FAFC),
    surfaceVariant = Color(0xFF1E2D45),
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = Color(0xFF2B3D59),
    outlineVariant = Color(0xFF1E293B)
)

@Composable
fun ScienceLensTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) ScienceLensDarkScheme else ScienceLensLightScheme
    val extendedColors = if (darkTheme) DarkScienceLensColors else LightScienceLensColors

    CompositionLocalProvider(LocalScienceLensColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    ScienceLensTheme(darkTheme = darkTheme, content = content)
}
