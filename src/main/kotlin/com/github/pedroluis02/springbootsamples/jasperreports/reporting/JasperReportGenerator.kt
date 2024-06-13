package com.github.pedroluis02.springbootsamples.jasperreports.reporting

import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.util.JRLoader
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import java.io.File

class JasperReportGenerator(private val template: String) {

    companion object {
        private const val TEMPLATE_DIR = "templates"
        private const val TEMPLATE_EXT = ".jrxml"
        private const val TEMPLATE_COMPILED_EXT = ".jasper"
    }

    private var isCompiled = false
    private var parameters = HashMap<String, Any>()
    private var dataSource: JRDataSource = JREmptyDataSource()

    fun compiled(compiled: Boolean) = apply {
        isCompiled = compiled
    }

    fun addDataSourceAsParameter(name: String, collection: Collection<*>) =
        addParameter(name, JRBeanCollectionDataSource(collection))

    fun addParameter(name: String, value: Any) = apply {
        parameters[name] = value
    }

    fun setDataSource(collection: Collection<*>) = apply {
        dataSource = JRBeanCollectionDataSource(collection)
    }

    fun generatePdf(output: String): File {
        val print = fillReport()
        JasperExportManager.exportReportToPdfFile(print, output)

        return File(output)
    }

    private fun fillReport(): JasperPrint {
        val report = loadReport()
        return JasperFillManager.fillReport(report, parameters, dataSource)
    }

    private fun loadReport(): JasperReport {
        return if (isCompiled) {
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