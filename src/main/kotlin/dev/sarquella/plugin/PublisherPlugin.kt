package dev.sarquella.plugin

import dev.sarquella.plugin.extensions.PublicationParamsExtension
import dev.sarquella.plugin.helper.extensions.applyPlugin
import dev.sarquella.plugin.helper.extensions.createExtension
import dev.sarquella.plugin.helper.extensions.registerTask
import dev.sarquella.plugin.tasks.publishing.GeneratePublication
import dev.sarquella.plugin.tasks.publishing.LoadCredentials
import dev.sarquella.plugin.tasks.publishing.PublishToMavenCentral
import dev.sarquella.plugin.tasks.publishing.SignPublication
import dev.sarquella.plugin.tasks.sources.GenerateJavadoc
import dev.sarquella.plugin.tasks.sources.GenerateJavadocJar
import dev.sarquella.plugin.tasks.sources.GenerateSourceJar
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.publish.plugins.PublishingPlugin

class PublisherPlugin : Plugin<Project> {

    companion object {
        const val PLUGIN_EXTENSION = "mavenCentralPublication"
        const val PLUGIN_GROUP = "mavencentral-publishing"
    }

    override fun apply(project: Project) {
        with(project) {
            createExtension(PLUGIN_EXTENSION, PublicationParamsExtension::class.java)

            applyPlugin(PublishingPlugin::class.java)
            applyPlugin(MavenPublishPlugin::class.java)

            registerTask(GenerateJavadoc::class.java)
            registerTask(GenerateJavadocJar::class.java)
            registerTask(GenerateSourceJar::class.java)
            registerTask(LoadCredentials::class.java)
            registerTask(GeneratePublication::class.java)
            registerTask(SignPublication::class.java)
            registerTask(PublishToMavenCentral::class.java)
        }
    }
}