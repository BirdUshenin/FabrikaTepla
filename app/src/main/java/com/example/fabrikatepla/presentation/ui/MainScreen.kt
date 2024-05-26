package com.example.fabrikatepla.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fabrikatepla.navigation.AppNavGraph
import com.example.fabrikatepla.navigation.NavBarItem
import com.example.fabrikatepla.presentation.ui.favorite.FavoriteScreen
import com.example.fabrikatepla.presentation.ui.home.CategoryItem.CategoryViewModel
import com.example.fabrikatepla.presentation.ui.home.HomeScreen
import com.example.fabrikatepla.presentation.ui.home.MainViewModel
import com.example.fabrikatepla.presentation.ui.profile.ProfileScreen
import com.example.fabrikatepla.presentation.ui.profile.ProfileViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    categoryViewModel: CategoryViewModel
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
    val navHostController = rememberNavController()

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    val fabIsVisible = remember {
        mutableStateOf(true)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(
                    NavBarItem.Home,
                    NavBarItem.Favorite,
                    NavBarItem.Profile
                )
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            navHostController.navigate(item.screen.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFFA6C37),
                            unselectedIconColor = Color.Black,
                            selectedTextColor = Color(0xFFFA6C37),
                            unselectedTextColor = Color.Black
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navHostController,
            homeScreenContent = {
                HomeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues,
                    categoryViewModel = categoryViewModel
                )
            },
            favoriteContent = {
                FavoriteScreen()
            },
            profileContent = {
                val profileViewModel = viewModel<ProfileViewModel>(viewModelStoreOwner)
                ProfileScreen(
                    viewModel = profileViewModel,
                    paddingValues = paddingValues,
                )
            }
        )
    }

}