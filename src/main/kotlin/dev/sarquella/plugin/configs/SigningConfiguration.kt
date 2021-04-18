package dev.sarquella.plugin.configs

import dev.sarquella.plugin.configs.base.Configuration
import dev.sarquella.plugin.helper.extensions.publishing
import dev.sarquella.plugin.helper.extensions.signing
import org.gradle.api.Project

object SigningConfiguration: Configuration {

    override fun configure(project: Project) {
        project.signing.sign(project.publishing.publications)
    }

}