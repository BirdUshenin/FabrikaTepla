package com.example.fabrikatepla.navigation

sealed class Screen(
    val route: String
) {
    object ProductMain: Screen(ROUTE_PRODUCT_MAIN)
    object Favorite: Screen(ROUTE_FAVORITE)
    object Profile: Screen(ROUTE_PROFILE)

    private companion object {
        const val ROUTE_PRODUCT_MAIN = "product_main"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"
    }
}