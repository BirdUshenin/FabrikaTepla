package com.example.fabrikatepla.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fabrikatepla.navigation.Screen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favoriteContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit,
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.ProductMain.route
    ) {
        composable(Screen.ProductMain.route) {
            homeScreenContent()
        }
        composable(Screen.Favorite.route) {
            favoriteContent()
        }
        composable(Screen.Profile.route) {
            profileContent()
        }
    }
}
