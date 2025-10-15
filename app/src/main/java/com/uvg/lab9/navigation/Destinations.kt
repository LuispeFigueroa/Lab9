package com.uvg.lab9.navigation

sealed class Destinations(val route: String) {
    object Root: Destinations("root")
    object Wishlist: Destinations("wishlist")
    object Profile: Destinations("profile")
    object Counter: Destinations("counter")
}