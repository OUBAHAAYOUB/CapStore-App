package com.example.capstore.data

import com.example.capstore.R

data class Cap(
    val id: Int,
    val name: String,
    val price: Double,
    val imageResId: Int
)

val dummyCapList = listOf(
    Cap(1, "Classic Baseball Cap", 18.00, R.drawable.cap_classic),
    Cap(2, "Trucker Hat", 20.00, R.drawable.cap_trucker),
    Cap(3, "Snapback Cap", 20.00, R.drawable.cap_snapback),
    Cap(4, "Dad Cap", 18.00, R.drawable.cap_dad),
    Cap(5, "Visor Cap", 15.00, R.drawable.cap_visor)
)