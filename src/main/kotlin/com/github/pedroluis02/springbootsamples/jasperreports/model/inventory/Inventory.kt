package com.github.pedroluis02.springbootsamples.jasperreports.model.inventory

import com.github.pedroluis02.springbootsamples.jasperreports.model.Device

data class Inventory(
    val header: InventoryHeader,
    val summary: List<InventorySummary>,
    val devices: List<Device>
)
