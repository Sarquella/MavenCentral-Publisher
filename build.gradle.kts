plugins {
    kotlin("jvm") version "1.3.72"
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.14.0"
}

group = "dev.sarquella"
version = "1.0"

repositories {
    mavenCentral()
    google()
}

dependencies {
    compileOnly("com.android.tools.build:gradle:4.1.3")
}

gradlePlugin {
    plugins {
        create("publisherPlugin") {
            id = "dev.sarquella.mavencentral-publisher"
            implementationClass = "dev.sarquella.plugin.PublisherPlugin"
            displayName = "MavenCentral-Publisher"
            description = "A gradle plugin to easily upload libraries to MavenCentral for distribution."
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

pluginBundle {
    website = "https://github.com/Sarquella/MavenCentral-Publisher"
    vcsUrl = "https://github.com/Sarquella/MavenCentral-Publisher"
    tags = listOf("library", "mavencentral", "publish", "sonatype")
}