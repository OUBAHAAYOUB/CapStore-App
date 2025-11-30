package com.example.capstore.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.capstore.data.local.CartItem
import com.example.capstore.navigation.AppBottomNavigation
import com.example.capstore.navigation.ScreenRoutes
import com.example.capstore.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = viewModel(factory = CartViewModel.Factory)
) {
    val cartItems by cartViewModel.cartItems.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            AppBottomNavigation(navController = navController, currentRoute = ScreenRoutes.Cart.route)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(cartItems) { item ->
                    CartListItem(
                        item = item,
                        onRemoveClick = { cartViewModel.removeFromCart(item) }
                    )
                }

                item { Spacer(Modifier.height(24.dp)) }

                item {
                    if (cartItems.isNotEmpty()) {
                        CartSummary(cartItems)
                    } else {
                        Text("Your cart is empty", modifier = Modifier.fillMaxWidth().padding(20.dp), color = Color.Gray)
                    }
                }
            }

            Button(
                onClick = { /* Handle checkout logic */ },
                modifier = Modifier.fillMaxWidth().padding(16.dp).height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Checkout", fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun CartListItem(item: CartItem, onRemoveClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = item.name,
            modifier = Modifier.size(70.dp).clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(item.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
            Text("$${"%.2f".format(item.price)}", color = Color.Gray, fontSize = 14.sp)
        }
        IconButton(onClick = onRemoveClick) {
            Icon(Icons.Default.Delete, contentDescription = "Remove", tint = Color.Red)
        }
    }
}

@Composable
private fun CartSummary(cartItems: List<CartItem>) {
    var subtotal = 0.0
    for (item in cartItems) {
        subtotal += item.price * item.quantity
    }

    val shipping = 5.00
    val total = subtotal + shipping

    Column(modifier = Modifier.fillMaxWidth()) {
        SummaryRow(title = "Subtotal", price = subtotal)
        SummaryRow(title = "Shipping", price = shipping)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        SummaryRow(title = "Total", price = total, isBold = true)
    }
}

@Composable
private fun SummaryRow(title: String, price: Double, isBold: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(title, fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal, color = if (isBold) Color.Black else Color.Gray)
        Text("$${"%.2f".format(price)}", fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal)
    }
    Spacer(Modifier.height(8.dp))
}