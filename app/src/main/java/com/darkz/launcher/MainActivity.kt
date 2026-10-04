package com.darkz.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.darkz.launcher.ui.home.LauncherHomeScreen
import com.darkz.launcher.ui.theme.DarkZLauncherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DarkZLauncherTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LauncherHomeScreen()
                }
            }
        }
    }
}
