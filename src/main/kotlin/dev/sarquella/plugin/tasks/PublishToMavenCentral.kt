package dev.sarquella.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class PublishToMavenCentral: DefaultTask() {

    @TaskAction
    fun publish() {
        val assemble = project.tasks.getByName("assembleRelease")
        val publish = project.tasks.getByName("publishReleasePublicationToMavencentralRepository")

        dependsOn.add(assemble)
        dependsOn.add(publish)

        publish.mustRunAfter(assemble)
    }

}