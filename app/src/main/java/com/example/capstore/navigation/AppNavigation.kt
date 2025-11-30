package com.example.capstore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.capstore.screens.cart.CartScreen
import com.example.capstore.screens.details.DetailsScreen
import com.example.capstore.screens.getstarted.GetStartedScreen
import com.example.capstore.screens.home.HomeScreen
import com.example.capstore.screens.login.LoginScreen
import com.example.capstore.screens.signup.SignUpScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.GetStarted.route
    ) {
        composable(route = ScreenRoutes.GetStarted.route) {
            GetStartedScreen(navController = navController)
        }

        composable(route = ScreenRoutes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = ScreenRoutes.SignUp.route) {
            SignUpScreen(navController = navController)
        }

        composable(route = ScreenRoutes.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = ScreenRoutes.Details.route,
            arguments = listOf(navArgument("capId") { type = NavType.IntType })
        ) { backStackEntry ->
            val capId = backStackEntry.arguments?.getInt("capId") ?: 1
            DetailsScreen(navController = navController, capId = capId)
        }

        composable(route = ScreenRoutes.Cart.route) {
            CartScreen(navController = navController)
        }
    }
}