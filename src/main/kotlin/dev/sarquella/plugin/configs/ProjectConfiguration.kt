package dev.sarquella.plugin.configs

import dev.sarquella.plugin.configs.base.Configuration
import dev.sarquella.plugin.helper.extensions.params
import dev.sarquella.plugin.helper.extensions.task
import dev.sarquella.plugin.tasks.GenerateJavadocJar
import dev.sarquella.plugin.tasks.GenerateSourceJar
import org.gradle.api.Project

object ProjectConfiguration: Configuration {

    override fun configure(project: Project) {
        val params = project.params
        val javadocJar = project.task(GenerateJavadocJar::class.java)
        val sourceJar = project.task(GenerateSourceJar::class.java)

        project.group = params.groupId.get()
        project.version = params.version.get()
        project.artifacts.add("archive", javadocJar)
        project.artifacts.add("archive", sourceJar)
    }

}