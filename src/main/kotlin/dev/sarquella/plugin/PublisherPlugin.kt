package dev.sarquella.plugin

import dev.sarquella.plugin.extension.PublisherPluginExtension
import dev.sarquella.plugin.task.PublishToMavenCentral
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class PublisherPlugin: Plugin<Project> {

    override fun apply(project: Project) {
       val extension = project.extensions.create("mavenCentralPublishing", PublisherPluginExtension::class.java)

        project.tasks.register("publishToMavenCentral", PublishToMavenCentral::class.java) { task ->
            task.doLast {
                println("publishedGroupId: ${extension.publishedGroupId.get()}")
                println("artifact: ${extension.artifact.get()}")
                println("version: ${extension.version.get()}")

                println("description: ${extension.description.get()}")

                println("webUrl: ${extension.webUrl.get()}")
                println("repoUrl: ${extension.repoUrl.get()}")
                println("vcsUrl: ${extension.vcsUrl.get()}")

                println("developerId: ${extension.developerId.get()}")
                println("developerName: ${extension.developerName.get()}")
                println("developerEmail: ${extension.developerEmail.get()}")

                println("licenseName: ${extension.licenseName.get()}")
                println("licenseUrl: ${extension.licenseUrl.get()}")

                println("propertiesFile: ${extension.propertiesFile.get()}")
            }
        }

    }

}