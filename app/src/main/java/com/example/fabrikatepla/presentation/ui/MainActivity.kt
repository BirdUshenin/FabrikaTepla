package com.example.fabrikatepla.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.example.fabrikatepla.presentation.theme.HeatFactoryTheme
import com.example.fabrikatepla.presentation.ui.home.CategoryItem.CategoryViewModel
import com.example.fabrikatepla.presentation.ui.home.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val categoryViewModel by viewModels<CategoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeatFactoryTheme {
                val systemUiController = rememberSystemUiController()
                val systemBarsColor = Color(0xFFFA6C37)
                MainScreen(viewModel, categoryViewModel)
                SideEffect {
                    systemUiController.setStatusBarColor(color = systemBarsColor)
                }
            }
        }
    }
}

