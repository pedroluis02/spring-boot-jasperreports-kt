package com.github.pedroluis02.springbootsamples.jasperreports.service

import com.github.pedroluis02.springbootsamples.jasperreports.model.inventory.Inventory
import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.util.JRLoader
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class InventoryReportGeneratorService {

    companion object {
        private const val TEMPLATE_NAME = "inventory"
        private const val TEMPLATE_EXT = ".jrxml"
        private const val TEMPLATE_COMPILED_EXT = ".jasper"
        private const val TEMPLATE_DIR = "templates"

        private const val SUMMARY_DATA_SOURCE_PARAM = "SUMMARY_DATA_SOURCE"
        private const val DEVICE_DATA_SOURCE_PARAM = "DEVICE_DATA_SOURCE"
    }

    fun generatePdf(data: Inventory, output: String, compiled: Boolean = false) {
        val print = fillReport(compiled, data)
        JasperExportManager.exportReportToPdfFile(print, output)
    }

    private fun fillReport(compiled: Boolean, data: Inventory): JasperPrint {
        val report = loadReport(compiled)

        val parameters = HashMap<String, Any>().apply {
            put(SUMMARY_DATA_SOURCE_PARAM, JRBeanCollectionDataSource(data.summary))
            put(DEVICE_DATA_SOURCE_PARAM, JRBeanCollectionDataSource(data.devices))
        }
        val dataSource = JRBeanCollectionDataSource(listOf(data.header))

        return JasperFillManager.fillReport(report, parameters, dataSource)
    }

    private fun loadReport(compiled: Boolean): JasperReport {
        val template = TEMPLATE_NAME
        return if (compiled) {
            val pathResource = loadTemplateResource(template + TEMPLATE_COMPILED_EXT)
            JRLoader.loadObject(pathResource.inputStream) as JasperReport
        } else {
            val pathResource = loadTemplateResource(template + TEMPLATE_EXT)
            JasperCompileManager.compileReport(pathResource.inputStream)
        }
    }

    private fun loadTemplateResource(template: String): Resource {
        return ClassPathResource("$TEMPLATE_DIR/$template")
    }
}
