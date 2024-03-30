package com.example.fabrikatepla.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fabrikatepla.R

sealed class NavBarItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {
    object Home: NavBarItem(
        screen = Screen.ProductMain,
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home
    )
//    data object Basket: NavBarItem(
//        titleResId = "",
//    )
    object Favorite: NavBarItem(
        screen = Screen.Favorite,
        titleResId = R.string.navigation_item_favorite,
        icon = Icons.Outlined.Favorite
    )
    object Profile: NavBarItem(
        screen = Screen.Profile,
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}