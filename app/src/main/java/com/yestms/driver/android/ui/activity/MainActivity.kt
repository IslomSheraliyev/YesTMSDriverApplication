package com.yestms.driver.android.ui.activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.yestms.driver.android.components.design.theme.YesTMSDriverApplicationTheme
import com.yestms.driver.android.ui.screens.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YesTMSDriverApplicationTheme {
                MainScreen()
            }
        }
    }
}