package com.github.pedroluis02.springbootsamples.jasperreports.sample

import com.github.pedroluis02.springbootsamples.jasperreports.model.Device
import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.Inventory
import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.InventoryHeader
import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.InventorySummary
import java.util.*
import java.util.stream.Collectors
import kotlin.random.Random

fun createInventorySample(): Inventory {
    val id = UUID.randomUUID().toString()
    val header = InventoryHeader(id, "General report of devices", "Administrator")

    return Inventory(header, createSummary(), createDevices())
}

private fun createSummary(): List<InventorySummary> {
    val random = Random(20)
    return (1..10).map {
        InventorySummary(
            it,
            "CODE-$it",
            "Company Name $it",
            random.nextInt(),
            random.nextInt(),
            random.nextInt()
        )
    }.stream().collect(Collectors.toList())
}

private fun createDevices(): List<Device> {
    return (1..20).map {
        Device(
            it,
            "TYPE-$it",
            "CODE-$it",
            "BRAND-$it",
            "MODEL-$it",
            Date(),
            "Responsible-$it",
            "ACTIVE"
        )
    }.stream().collect(Collectors.toList())
}