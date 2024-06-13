package com.github.pedroluis02.springbootsamples.jasperreports

import com.github.pedroluis02.springbootsamples.jasperreports.model.Device
import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.Inventory
import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.InventoryHeader
import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.InventorySummary
import com.github.pedroluis02.springbootsamples.jasperreports.service.InventoryReportGeneratorService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*
import java.util.stream.Collectors
import kotlin.random.Random

class InventoryReportGeneratorTest {

    lateinit var service: InventoryReportGeneratorService

    @BeforeEach
    fun setup() {
        service = InventoryReportGeneratorService()
    }

    @Test
    fun shouldGeneratePdf() {
        val data = createInventory()
        val output = System.currentTimeMillis().toString() + ".pdf"

        service.generatePdf(data, output)
        assertThat(File(output)).exists()
    }

    private fun createInventory(): Inventory {
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
}