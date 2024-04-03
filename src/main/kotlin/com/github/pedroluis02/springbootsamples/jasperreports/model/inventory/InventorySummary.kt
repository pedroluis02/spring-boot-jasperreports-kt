package com.github.pedroluis02.springbootsamples.jasperreports.model.inventory

data class InventorySummary(
    val id: Int,
    val companyCode: String,
    val companyName: String,
    val employeesTotal: Int,
    val customersTotal: Int,
    val devicesTotal: Int
)
