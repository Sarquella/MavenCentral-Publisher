package dev.sarquella.plugin

import dev.sarquella.plugin.configs.CredentialsConfiguration
import dev.sarquella.plugin.configs.PublicationConfiguration
import dev.sarquella.plugin.configs.SigningConfiguration
import dev.sarquella.plugin.extensions.PublicationParamsExtension
import dev.sarquella.plugin.helper.extensions.applyPlugin
import dev.sarquella.plugin.helper.extensions.configure
import dev.sarquella.plugin.helper.extensions.createExtension
import dev.sarquella.plugin.helper.extensions.registerTask
import dev.sarquella.plugin.tasks.PublishToMavenCentral
import dev.sarquella.plugin.tasks.GenerateJavadoc
import dev.sarquella.plugin.tasks.GenerateJavadocJar
import dev.sarquella.plugin.tasks.GenerateSourceJar
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.publish.plugins.PublishingPlugin
import org.gradle.plugins.signing.SigningPlugin

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
            applyPlugin(SigningPlugin::class.java)

            registerTask(GenerateJavadoc::class.java)
            registerTask(GenerateJavadocJar::class.java)
            registerTask(GenerateSourceJar::class.java)
            registerTask(PublishToMavenCentral::class.java)

            afterEvaluate {
                configure(CredentialsConfiguration)
                configure(SigningConfiguration)
                configure(PublicationConfiguration)
            }
        }
    }
}