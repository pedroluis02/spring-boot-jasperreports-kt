package com.github.pedroluis02.springbootsamples.jasperreports.model.inventory

data class Inventory(
    val header: InventoryHeader,
    val summary: List<InventorySummary>
)
