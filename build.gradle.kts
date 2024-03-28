import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
}

group = "com.github.pedroluis02.springbootsamples"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    val jasperReportsVersion = "6.21.2"
    val xmlGraphicsVersion = "1.17"
    val barcode4jVersion = "2.1"
    val zxingVersion = "3.5.3"

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("net.sf.jasperreports:jasperreports:$jasperReportsVersion")

    runtimeOnly("org.apache.xmlgraphics:batik-bridge:$xmlGraphicsVersion")
    runtimeOnly("net.sf.barcode4j:barcode4j:$barcode4jVersion")
    runtimeOnly("com.google.zxing:javase:$zxingVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
