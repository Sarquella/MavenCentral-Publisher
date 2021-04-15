package dev.sarquella.plugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class PublishToMavenCentral: DefaultTask() {

    init {
        group = "publishing"
    }

    @TaskAction
    fun publish() {
        println("Hello from PublisherPlugin")
    }

}