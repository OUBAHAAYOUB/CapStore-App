package com.example.capstore.navigation

sealed class ScreenRoutes(val route: String) {
    object GetStarted : ScreenRoutes("get_started_screen")
    object Login : ScreenRoutes("login_screen")
    object SignUp : ScreenRoutes("signup_screen")
    object Home : ScreenRoutes("home_screen")

    object Details : ScreenRoutes("details_screen/{capId}") {
        fun createRoute(capId: Int) = "details_screen/$capId"
    }

    object Cart : ScreenRoutes("cart_screen")
}