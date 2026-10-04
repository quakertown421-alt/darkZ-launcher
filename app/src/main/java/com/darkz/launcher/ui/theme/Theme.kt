package com.darkz.launcher.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkZPrimary,
    secondary = DarkZSecondary,
    tertiary = DarkZAccent,
    background = DarkZBackground,
    surface = DarkZSurface,
    onPrimary = DarkZOnPrimary,
    onSurface = DarkZOnSurface
)

private val LightColorScheme = lightColorScheme(
    primary = DarkZPrimary,
    secondary = DarkZSecondary,
    tertiary = DarkZAccent,
    background = DarkZBackground,
    surface = DarkZSurface,
    onPrimary = DarkZOnPrimary,
    onSurface = DarkZOnSurface
)

@Composable
fun DarkZLauncherTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
