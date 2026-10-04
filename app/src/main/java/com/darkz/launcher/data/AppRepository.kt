package com.darkz.launcher.data

import com.darkz.launcher.model.AppEntry
import com.darkz.launcher.model.AppGroup
import com.darkz.launcher.model.LauncherPreferences

class AppRepository {
    private val defaultApps = listOf(
        AppEntry("com.android.camera", "Camera", category = "Tools"),
        AppEntry("com.android.chrome", "Chrome", category = "Productivity"),
        AppEntry("com.android.settings", "Settings", category = "System"),
        AppEntry("com.spotify.music", "Spotify", category = "Media"),
        AppEntry("com.google.android.apps.messaging", "Messages", category = "Communication"),
        AppEntry("com.google.android.apps.translate", "Translate", category = "Utility"),
        AppEntry("com.zendesk.android", "Games", category = "Games", isGame = true),
        AppEntry("com.darkz.mod", "Mod Hub", category = "Games", isGame = true),
        AppEntry("com.android.gallery3d", "Gallery", category = "Media"),
        AppEntry("com.android.dialer", "Phone", category = "Communication"),
        AppEntry("com.android.email", "Email", category = "Productivity")
    )

    val defaultGroups = listOf(
        AppGroup("games", "Games", listOf("com.zendesk.android", "com.darkz.mod")),
        AppGroup("media", "Media", listOf("com.spotify.music", "com.android.gallery3d")),
        AppGroup("tools", "Tools", listOf("com.android.camera", "com.android.settings"))
    )

    fun getApps(): List<AppEntry> = defaultApps

    fun getDefaultPreferences(): LauncherPreferences = LauncherPreferences()
}
