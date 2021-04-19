package dev.sarquella.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.Task

abstract class PublishToMavenCentral: DefaultTask() {

    private var assemble: Task? = null
    private var publish: Task? = null

    init {
        project.tasks.whenTaskAdded { task ->
            when(task.name) {
                "assembleRelease" -> {
                    dependsOn.add(task)
                    assemble = task
                }
                "publishReleasePublicationToMavencentralRepository" -> {
                    dependsOn.add(task)
                    publish = task
                }
            }
        }

        publish?.mustRunAfter(assemble)
    }

}