package dev.sarquella.plugin.tasks.publishing

import dev.sarquella.plugin.helper.extensions.publishing
import dev.sarquella.plugin.helper.extensions.signing
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class SignPublication: DefaultTask() {

    init {
        val generatePublication = project.tasks.withType(GeneratePublication::class.java).first()
        dependsOn.add(generatePublication)
    }

    @TaskAction
    fun sign() {
        project.signing.sign(project.publishing.publications)
    }

}