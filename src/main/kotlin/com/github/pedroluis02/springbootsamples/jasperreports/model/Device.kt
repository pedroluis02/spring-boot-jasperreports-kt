package com.github.pedroluis02.springbootsamples.jasperreports.model

import java.util.*

data class Device(
    val index: Int,
    val type: String,
    val code: String,
    val brand: String,
    val model: String,
    val purchaseDate: Date,
    val responsible: String,
    val status: String,
)
