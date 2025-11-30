package com.example.capstore.data.repository

import com.example.capstore.data.local.CartDao
import com.example.capstore.data.local.CartItem
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {

    val allCartItems: Flow<List<CartItem>> = cartDao.getAllCartItems()

    suspend fun addToCart(item: CartItem) {
        cartDao.addToCart(item)
    }

    suspend fun removeFromCart(item: CartItem) {
        cartDao.removeFromCart(item)
    }
}