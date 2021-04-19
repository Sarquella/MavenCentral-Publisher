plugins {
    kotlin("jvm") version "1.3.72"
    id("java-gradle-plugin")
    id("maven-publish")
}

group = "dev.sarquella"
version = "1.0"

repositories {
    mavenCentral()
    google()
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72")
    compileOnly("com.android.tools.build:gradle:4.1.3")
}

gradlePlugin {
    plugins {
        create("publisherPlugin") {
            id = "dev.sarquella.mavencentral-publisher"
            implementationClass = "dev.sarquella.plugin.PublisherPlugin"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}