package com.darkz.launcher.model

import kotlinx.serialization.Serializable

@Serializable
data class AppEntry(
    val packageName: String,
    val label: String,
    val category: String = "Apps",
    val isGame: Boolean = false,
    val iconResName: String? = null,
    val pinned: Boolean = false
)

@Serializable
data class AppGroup(
    val id: String,
    val name: String,
    val apps: List<String>
)

@Serializable
data class LauncherPreferences(
    val wallpaperUrl: String = "",
    val iconPack: String = "Default",
    val gridColumns: Int = 4,
    val showWidgets: Boolean = true,
    val dockVisible: Boolean = true,
    val autoSort: Boolean = true,
    val accentColor: String = "#67E8F9",
    val enableDoubleTapCustomization: Boolean = true,
    val defaultLauncherPrompt: Boolean = true,
    val gameModMenuEnabled: Boolean = true,
    val animationStyle: String = "Dark Pulse"
)
