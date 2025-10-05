package com.uvg.lab9.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Root.route
    ) {
        composable(Destinations.Root.route) {}
        composable(Destinations.Wishlist.route) {}
        composable(Destinations.Profile.route) {}
    }
}