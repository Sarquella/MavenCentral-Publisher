package dev.sarquella.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class PublishToMavenCentral: DefaultTask() {

    @TaskAction
    fun publish() {
        println("Hello from PublisherPlugin")
    }

}