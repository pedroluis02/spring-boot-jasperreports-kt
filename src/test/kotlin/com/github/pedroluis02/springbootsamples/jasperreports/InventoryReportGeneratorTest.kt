package com.github.pedroluis02.springbootsamples.jasperreports

import com.github.pedroluis02.springbootsamples.jasperreports.sample.createInventorySample
import com.github.pedroluis02.springbootsamples.jasperreports.service.InventoryReportGeneratorService
import com.github.pedroluis02.springbootsamples.jasperreports.service.InventoryReportGeneratorServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InventoryReportGeneratorTest {

    lateinit var service: InventoryReportGeneratorService

    @BeforeEach
    fun setup() {
        service = InventoryReportGeneratorServiceImpl()
    }

    @Test
    fun shouldGeneratePdf() {
        val data = createInventorySample()
        val output = "test-" + System.currentTimeMillis().toString() + ".pdf"

        val file = service.generatePdf(data, output, false)
        assertThat(file).exists()

        assertThat(file.delete()).isTrue()
    }
}