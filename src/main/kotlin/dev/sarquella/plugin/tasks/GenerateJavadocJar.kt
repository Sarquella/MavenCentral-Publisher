package dev.sarquella.plugin.tasks

import org.gradle.jvm.tasks.Jar

abstract class GenerateJavadocJar: Jar() {

    private val javadocTask = project.tasks.withType(GenerateJavadoc::class.java).first()

    init {
        dependsOn.add(javadocTask)
    }

    override fun copy() {
        archiveClassifier.set("javadoc")
        from(javadocTask.destinationDir)
        super.copy()
    }

}