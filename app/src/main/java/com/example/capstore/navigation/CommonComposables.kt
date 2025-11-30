package com.example.capstore.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capstore.navigation.ScreenRoutes


@Composable
fun AppBottomNavigation(navController: NavController, currentRoute: String) {

    BottomAppBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        IconButton(
            onClick = { navController.navigate(ScreenRoutes.Home.route) },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home",
                tint = if (currentRoute == ScreenRoutes.Home.route) Color.Black else Color.Gray
            )
        }

        IconButton(
            onClick = { navController.navigate(ScreenRoutes.Home.route) },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.Store,
                contentDescription = "Shop",
                tint = Color.Gray
            )
        }

        IconButton(
            onClick = { navController.navigate(ScreenRoutes.Cart.route) },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Cart",
                tint = if (currentRoute == ScreenRoutes.Cart.route) Color.Black else Color.Gray
            )
        }

        IconButton(
            onClick = {  },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile",
                tint = Color.Gray
            )
        }
    }
}