description = "Service for holding retrospectives software development teams"

java {
    group = "ru.microsrv"
    version = "0.0.1"
    sourceCompatibility = JavaVersion.VERSION_21
}

plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.kapt)
}

dependencies {
    implementation(libs.springboot.starter.data.jpa)
    implementation(libs.postgresql)
    implementation(libs.springboot.starter.undertow)
    implementation(libs.springboot.starter.web) {
        exclude(module = "spring-boot-starter-tomcat")
    }
//    implementation(libs.springboot.starter.security) // todo: configure security context
    implementation(libs.springboot.starter.validation)

    implementation(libs.jackson.module.kotlin)
    implementation(libs.kotlin.reflect)

    implementation(libs.mapstruct)
    kapt(libs.mapstruct.processor)

    implementation(libs.springboot.starter.logging) {
        exclude(module = "log4j-api")
        exclude(module = "log4j-to-slf4j")
    }

    testImplementation(libs.springboot.starter.test)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
    }
}
