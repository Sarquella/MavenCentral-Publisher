package dev.sarquella.plugin

import dev.sarquella.plugin.task.PublishToMavenCentral
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class PublisherPlugin: Plugin<Project> {

    override fun apply(project: Project) {

        project.tasks.register("publishToMavenCentral", PublishToMavenCentral::class.java) {

        }

    }

}