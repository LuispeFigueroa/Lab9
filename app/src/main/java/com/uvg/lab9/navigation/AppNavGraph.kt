package com.uvg.lab9.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.lab9.LikeCounterScreen
import com.uvg.lab9.feature.profile.presentation.ProfileScreen
import com.uvg.lab9.feature.wishlist.presentation.WishlistScreen
import com.uvg.lab9.feature.wishlist.presentation.WishlistViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Wishlist.route,
        route = Destinations.Root.route
    ) {
        composable(Destinations.Wishlist.route) { backStackEntry ->
            // obtiene el entry raíz
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Destinations.Root.route)
            }

            val wishlistViewModel: WishlistViewModel = viewModel(parentEntry)

            WishlistScreen(
                viewModel = wishlistViewModel,
                onNavigateToProfile = {
                    navController.navigate(Destinations.Profile.route)
                },
                onNavigateToCounter = {
                    navController.navigate(Destinations.Counter.route)
                }
            )
        }

        composable(Destinations.Profile.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Destinations.Root.route)
            }

            val wishlistViewModel: WishlistViewModel = viewModel(parentEntry)

            ProfileScreen(
                viewModel = wishlistViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Destinations.Counter.route) {
            LikeCounterScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}

