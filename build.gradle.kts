plugins {
    kotlin("jvm") version "1.3.72"
    id("java-gradle-plugin")
    id("maven-publish")
}

group = "dev.sarquella"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {}

gradlePlugin {
    plugins {
        create("publisherPlugin") {
            id = "dev.sarquella.mavencentral-publisher"
            implementationClass = "dev.sarquella.plugin.PublisherPlugin"
        }
    }
}