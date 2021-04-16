package dev.sarquella.plugin.tasks.publishing

import dev.sarquella.plugin.helper.extensions.params
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.util.*

abstract class LoadCredentials: DefaultTask() {

    @TaskAction
    fun load() {
        val params = project.params

        val credentials = Properties().apply {
            load(project.rootProject.file(params.propertiesFile.get()).inputStream())
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