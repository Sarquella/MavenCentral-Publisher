package dev.sarquella.plugin.tasks.sources

import dev.sarquella.plugin.helper.extensions.android
import dev.sarquella.plugin.helper.extensions.isAndroidLibrary
import org.gradle.api.tasks.javadoc.Javadoc
import java.io.File

abstract class GenerateJavadoc: Javadoc() {

    override fun generate() {
        if(project.isAndroidLibrary) {
            setSource("android.sourceSets.main.java.srcDirs")
            exclude("**/*.kt")
            classpath += project.files(project.android.bootClasspath.joinToString(File.pathSeparator))
        }

        super.generate()
    }

}