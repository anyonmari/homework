plugins {
    id("java")
    java
    kotlin("jvm") version "1.6.10"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation("org.testng:testng:7.4.0")

    implementation(kotlin("stdlib-jdk8"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

}

tasks.test {
    useJUnitPlatform()
    useTestNG()
}

// Перемещаем зависимость kotlin-script-runtime в buildscript
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-script-runtime:1.6.0")
    }
}