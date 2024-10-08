description = "Team retro migration job"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

plugins {
    java
    alias(libs.plugins.liquibase.gradle)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    implementation(libs.liquibase.core)
    implementation(libs.springboot.starter.jdbc)
    runtimeOnly(libs.postgresql)
    liquibaseRuntime(libs.postgresql)
    liquibaseRuntime(libs.liquibase.core)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

liquibase {
    activities.register("main") {
        // Настройки БД для локальной разработки
        this.arguments = mapOf(
            "logLevel" to "info",
            "changelogFile" to "db/changelog/db.changelog.yaml",
            "classpath" to "src/main/resources/",
            "url" to "jdbc:postgresql://localhost:5469/team_retro_db",
            "username" to "team_retro_db_admin",
            "password" to "team_retro_db_admin_password",
            "driver" to "org.postgresql.Driver"
        )
    }
    runList = "main"
}
