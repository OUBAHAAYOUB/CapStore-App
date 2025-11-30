package com.example.capstore.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.capstore.R
import com.example.capstore.data.local.CartItem
import com.example.capstore.navigation.AppBottomNavigation
import com.example.capstore.navigation.ScreenRoutes
import com.example.capstore.viewmodel.CartViewModel
import com.example.capstore.data.dummyCapList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    capId: Int,
    cartViewModel: CartViewModel = viewModel(factory = CartViewModel.Factory)
) {
    val cap = dummyCapList.find { it.id == capId } ?: dummyCapList[0]
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cap Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            AppBottomNavigation(navController = navController, currentRoute = ScreenRoutes.Home.route)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = cap.imageResId),
                    contentDescription = cap.name,//++
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(cap.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text("$${cap.price}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                    Spacer(Modifier.height(24.dp))
                    DetailRow(title = "Material", value = "Cotton")
                    DetailRow(title = "Fit", value = "Adjustable")
                    DetailRow(title = "Care", value = "Hand Wash")
                }
            }

            Button(
                onClick = {
                    val item = CartItem(
                        name = cap.name,
                        price = cap.price,
                        imageResId = cap.imageResId,
                        quantity = 1
                    )

                    cartViewModel.addToCart(item)

                    navController.navigate(ScreenRoutes.Cart.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Add to Cart", fontSize = 16.sp)
            }
        }
    }
}


@Composable
private fun DetailRow(title: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, color = Color.Gray)
        Text(text = value, fontWeight = FontWeight.Medium)
    }
    Spacer(Modifier.height(8.dp))
}