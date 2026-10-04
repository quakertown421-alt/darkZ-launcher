package com.darkz.launcher.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkz.launcher.data.AppRepository
import com.darkz.launcher.model.AppEntry
import com.darkz.launcher.ui.theme.DarkZBackground
import com.darkz.launcher.ui.theme.DarkZPrimary
import com.darkz.launcher.ui.theme.DarkZSecondary
import com.darkz.launcher.ui.theme.DarkZSurface

@Composable
fun LauncherHomeScreen() {
    val repository = remember { AppRepository() }
    val apps = remember { repository.getApps() }
    val groups = remember { repository.defaultGroups }
    var showModMenu by remember { mutableStateOf(false) }
    var showSettings by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkZBackground,
                        Color(0xFF0F172A),
                        DarkZSecondary.copy(alpha = 0.7f)
                    )
                )
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        showSettings = true
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 30.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopStatusBar()

            Column {
                FeatureBanner()
                Spacer(modifier = Modifier.height(18.dp))
                AppGroupRow(groups)
                Spacer(modifier = Modifier.height(18.dp))
                LauncherGrid(apps)
            }

            DockBar(onGameClick = { showModMenu = true }, onSettingsClick = { showSettings = true })
        }

        if (showModMenu) {
            GameModMenu(onClose = { showModMenu = false })
        }

        if (showSettings) {
            CustomizationPanel(onClose = { showSettings = false })
        }
    }
}

@Composable
private fun TopStatusBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "9:41",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("4G", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
            Text("94%", color = DarkZPrimary)
        }
    }
}

@Composable
private fun FeatureBanner() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = DarkZSurface.copy(alpha = 0.7f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "darkZ launcher",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "4K wallpaper + optimized mod tools",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(listOf(DarkZPrimary, DarkZSecondary))),
                contentAlignment = Alignment.Center
            ) {
                Text("Z", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun AppGroupRow(groups: List<com.darkz.launcher.model.AppGroup>) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        groups.forEach { group ->
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(88.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = DarkZSurface.copy(alpha = 0.82f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = when (group.name.lowercase()) {
                            "games" -> Icons.Default.Gamepad
                            "media" -> Icons.Default.Apps
                            else -> Icons.Default.Smartphone
                        },
                        contentDescription = null,
                        tint = DarkZPrimary
                    )
                    Text(group.name, color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}

@Composable
private fun LauncherGrid(apps: List<AppEntry>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(apps) { app ->
            AppTile(app)
        }
    }
}

@Composable
private fun AppTile(app: AppEntry) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(DarkZSurface.copy(alpha = 0.85f))
            .padding(vertical = 8.dp)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (app.isGame) DarkZPrimary.copy(alpha = 0.18f)
                    else DarkZSecondary.copy(alpha = 0.18f)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = app.label.firstOrNull()?.uppercase() ?: "A",
                color = DarkZPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = app.label,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 11.sp,
            maxLines = 1
        )
    }
}

@Composable
private fun DockBar(onGameClick: () -> Unit, onSettingsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(DarkZSurface.copy(alpha = 0.88f))
            .padding(horizontal = 18.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DockButton(Icons.Default.Apps, "Apps")
        DockButton(Icons.Default.Gamepad, "Mods", onTap = onGameClick)
        DockButton(Icons.Default.Widgets, "Widgets")
        DockButton(Icons.Default.Settings, "Setup", onTap = onSettingsClick)
    }
}

@Composable
private fun DockButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onTap: (() -> Unit)? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onTap?.invoke() }
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = DarkZPrimary,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
            fontSize = 10.sp
        )
    }
}

@Composable
private fun GameModMenu(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(280.dp),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = DarkZSurface)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Game Mod Menu", fontWeight = FontWeight.Bold)
                    Text("X", modifier = Modifier.clickable { onClose() }, color = DarkZPrimary)
                }
                Spacer(modifier = Modifier.height(18.dp))
                listOf("Boost FPS", "No recoil", "Wallhack", "Unlimited ammo", "Low latency").forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item, color = MaterialTheme.colorScheme.onSurface)
                        Text("ON", color = DarkZPrimary)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkZPrimary.copy(alpha = 0.2f))
                ) {
                    Text(
                        text = "Minimize to Z",
                        modifier = Modifier.padding(14.dp),
                        color = DarkZPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun CustomizationPanel(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.45f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(330.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = DarkZSurface)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Customization", fontWeight = FontWeight.Bold)
                    Text("X", modifier = Modifier.clickable { onClose() }, color = DarkZPrimary)
                }
                Spacer(modifier = Modifier.height(14.dp))
                listOf(
                    "App icons",
                    "Widgets",
                    "App dock",
                    "Auto sorter",
                    "4K wallpaper",
                    "Gesture shortcuts",
                    "Game optimization presets"
                ).forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(option, color = MaterialTheme.colorScheme.onSurface)
                        Text("ON", color = DarkZPrimary)
                    }
                }
            }
        }
    }
}
