package com.example.fabrikatepla.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fabrikatepla.presentation.ui.profile.ProfileViewModel

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favoriteContent: @Composable () -> Unit,
    profileContent: @Composable (ProfileViewModel) -> Unit,
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
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
            val viewModel = viewModel<ProfileViewModel>(viewModelStoreOwner)
            profileContent(viewModel)
        }
    }
}
