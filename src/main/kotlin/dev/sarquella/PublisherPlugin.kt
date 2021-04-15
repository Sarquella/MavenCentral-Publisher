package dev.sarquella

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class PublisherPlugin: Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.create("publishToMavenCentral") { task ->
            task.doLast {
                println("Hello from PublisherPlugin")
            }
        }.apply {
            group = "publishing"
        }
    }

}