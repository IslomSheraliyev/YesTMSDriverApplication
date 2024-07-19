package com.yestms.driver.android.ui.activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.yestms.driver.android.components.design.theme.YesTMSDriverApplicationTheme
import com.yestms.driver.android.ui.screens.main.MainScreen
import com.yestms.driver.android.ui.screens.main.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainScreenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YesTMSDriverApplicationTheme {
                MainScreen()
            }
        }
    }

    override fun onStart() {
        viewModel.connect()
        super.onStart()
    }

    override fun onPause() {
        viewModel.disconnect()
        super.onPause()
    }

    override fun onDestroy() {
        viewModel.disconnect()
        super.onDestroy()
    }

}