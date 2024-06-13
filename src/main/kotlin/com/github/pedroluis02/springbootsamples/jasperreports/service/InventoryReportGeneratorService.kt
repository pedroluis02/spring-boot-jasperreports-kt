package com.github.pedroluis02.springbootsamples.jasperreports.service

import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.Inventory
import java.io.File

fun interface InventoryReportGeneratorService {
    fun generatePdf(data: Inventory, output: String, compiled: Boolean): File
}