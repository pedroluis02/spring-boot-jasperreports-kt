package com.github.pedroluis02.springbootsamples.jasperreports.service

import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.Inventory
import com.github.pedroluis02.springbootsamples.jasperreports.reporting.JasperReportGenerator
import org.springframework.stereotype.Service
import java.io.File

@Service
class InventoryReportGeneratorServiceImpl : InventoryReportGeneratorService {

    companion object {
        private const val TEMPLATE_NAME = "inventory"
        private const val SUMMARY_DATA_SOURCE_PARAM = "SUMMARY_DATA_SOURCE"
        private const val DEVICE_DATA_SOURCE_PARAM = "DEVICE_DATA_SOURCE"
    }

    override fun generatePdf(data: Inventory, output: String, compiled: Boolean): File {
        return JasperReportGenerator(TEMPLATE_NAME)
            .compiled(compiled)
            .setDataSource(listOf(data.header))
            .addDataSourceAsParameter(SUMMARY_DATA_SOURCE_PARAM, data.summary)
            .addDataSourceAsParameter(DEVICE_DATA_SOURCE_PARAM, data.devices)
            .generatePdf(output)
    }
}
