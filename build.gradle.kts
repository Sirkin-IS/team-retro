import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	repositories {
		mavenCentral()
	}
}

allprojects {
	group = "ru.microsrv"
	version = "0.0.1"
	description = "Service for holding retrospectives software development teams"

	repositories {
		rootProject.buildscript.repositories.forEach { repositories.add(it) }
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

subprojects {
	apply {
		plugin("io.spring.dependency-management")
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

plugins {
	java
	id("io.spring.dependency-management") version "1.1.3"
	id("org.springframework.boot") version "3.1.3" apply false
	kotlin("jvm") version "1.8.22" apply false
	kotlin("plugin.spring") version "1.8.22" apply false
	kotlin("plugin.jpa") version "1.8.22" apply false
	kotlin("kapt") version "1.8.22" apply false
}
