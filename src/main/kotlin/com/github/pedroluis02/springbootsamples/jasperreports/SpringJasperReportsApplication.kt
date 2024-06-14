package com.github.pedroluis02.springbootsamples.jasperreports

import com.github.pedroluis02.springbootsamples.jasperreports.sample.createInventorySample
import com.github.pedroluis02.springbootsamples.jasperreports.service.InventoryReportGeneratorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringJasperReportsApplication : CommandLineRunner {

    @Autowired
    private lateinit var service: InventoryReportGeneratorService

    override fun run(vararg args: String?) {
        val data = createInventorySample()
        val output = "cmd-" + System.currentTimeMillis().toString() + ".pdf"
        service.generatePdf(data, output, false)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringJasperReportsApplication>(*args)
}
