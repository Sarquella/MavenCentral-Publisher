package dev.sarquella.plugin.tasks.sources

import dev.sarquella.plugin.helper.extensions.isAndroidLibrary
import org.gradle.jvm.tasks.Jar

abstract class GenerateSourceJar: Jar() {

    override fun copy() {
        archiveClassifier.set("sources")
        if (project.isAndroidLibrary) {
            from("android.sourceSets.main.java.srcDirs")
            from("android.sourceSets.main.kotlin.srcDirs")
        } else {
            from("sourceSets.main.java.srcDirs")
            from("sourceSets.main.kotlin.srcDirs")
        }

        super.copy()
    }

}