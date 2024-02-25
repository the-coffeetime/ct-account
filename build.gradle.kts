import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
	kotlin("plugin.allopen") version "1.9.22"
	kotlin("kapt") version "1.9.22"
}

group = "com.coffeetime"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

configurations {
	all {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
}

dependencies {
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	implementation("org.springframework.plugin:spring-plugin-core:2.0.0.RELEASE")
	implementation("org.springframework:spring-core:6.1.3")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core")
	}
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("com.ninja-squad:springmockk:4.0.2")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	kapt("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("io.springfox:springfox-swagger2:2.8.0")
	implementation("io.springfox:springfox-swagger-ui:2.8.0")

	// RDBMS
	implementation("com.zaxxer:HikariCP:4.0.3")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("org.mariadb.jdbc:mariadb-java-client:3.3.2")

	// Error handling
	implementation("org.thymeleaf:thymeleaf:3.1.2.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
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

dependencies {
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine") // JUnit 4 제외
	}
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
	testImplementation("org.mockito:mockito-core:3.6.28")
	testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.Embeddable")
	annotation("jakarta.persistence.MappedSuperclass")
}

kapt {
	correctErrorTypes = true
}