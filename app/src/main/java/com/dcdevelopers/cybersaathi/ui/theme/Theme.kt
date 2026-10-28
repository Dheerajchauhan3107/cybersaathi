package com.dcdevelopers.cybersaathi.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CyberDarkColorScheme = darkColorScheme(
    primary = CyanCyber,
    secondary = BlueCyber,
    background = BackgroundCyber,
    surface = SurfaceDarkCyber,
    surfaceVariant = SurfaceCardCyber,
    onBackground = MainTextCyber,
    onSurface = MainTextCyber,
    onSurfaceVariant = MutedTextCyber
)

private val OledBlackColorScheme = darkColorScheme(
    primary = CyanCyber,
    secondary = BlueCyber,
    background = OledBackground,
    surface = OledSurface,
    surfaceVariant = OledCard,
    onBackground = MainTextCyber,
    onSurface = MainTextCyber,
    onSurfaceVariant = MutedTextCyber
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF007A78),
    secondary = Color(0xFF1B55B0),
    background = LightBackground,
    surface = LightSurface,
    surfaceVariant = LightCard,
    onBackground = LightText,
    onSurface = LightText,
    onSurfaceVariant = LightMutedText
)

@Composable
fun CyberSaathiTheme(
    themePreference: String = "CYBER_DARK",
    content: @Composable () -> Unit
) {
    val colorScheme = when (themePreference) {
        "OLED_BLACK" -> OledBlackColorScheme
        "LIGHT" -> LightColorScheme
        else -> CyberDarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
