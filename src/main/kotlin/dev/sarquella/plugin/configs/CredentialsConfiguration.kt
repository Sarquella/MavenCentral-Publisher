package dev.sarquella.plugin.configs

import dev.sarquella.plugin.configs.base.Configuration
import dev.sarquella.plugin.helper.extensions.params
import org.gradle.api.Project
import java.util.*

object CredentialsConfiguration: Configuration {

    private const val DEFAULT_FILE = "local.properties"

    override fun configure(project: Project) {
        val params = project.params

        val credentials = Properties().apply {
            load(project.rootProject.file(params.propertiesFile.orNull ?: DEFAULT_FILE).inputStream())
        }

        val properties = project.extensions.extraProperties

        //Sign
        properties.set("signing.keyId", credentials.getProperty("signingKeyId", ""))
        properties.set("signing.password", credentials.getProperty("signingKeyPassword", ""))
        properties.set("signing.secretKeyRingFile", credentials.getProperty("signingKeyFile", ""))

        //Auth
        properties.set("ossrhUsername", credentials.getProperty("ossrhUsername", ""))
        properties.set("ossrhPassword", credentials.getProperty("ossrhPassword", ""))
    }

}