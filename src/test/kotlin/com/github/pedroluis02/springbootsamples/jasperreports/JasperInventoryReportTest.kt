package com.github.pedroluis02.springbootsamples.jasperreports

import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.Inventory
import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.InventoryHeader
import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.InventorySummary
import com.github.pedroluis02.springbootsamples.jasperreports.service.JasperInventoryReportService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*
import java.util.stream.Collectors
import kotlin.random.Random

class JasperInventoryReportTest {

    lateinit var service: JasperInventoryReportService

    @BeforeEach
    fun setup() {
        service = JasperInventoryReportService()
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
        val header = InventoryHeader(id, "General report", "Administrator")

        return Inventory(header, createSummary())
    }

    private fun createSummary(): List<InventorySummary> {
        val random = Random(20)
        return (1..12).map {
            InventorySummary(
                it,
                "code-$it",
                "Company Name $it",
                random.nextInt(),
                random.nextInt(),
                random.nextInt()
            )
        }.stream().collect(Collectors.toList())
    }
}