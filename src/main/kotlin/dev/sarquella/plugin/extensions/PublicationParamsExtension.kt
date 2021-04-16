package dev.sarquella.plugin.extensions

import org.gradle.api.provider.Property

abstract class PublicationParamsExtension {
    abstract val groupId: Property<String>
    abstract val artifact: Property<String>
    abstract val version: Property<String>

    abstract val description: Property<String>

    abstract val webUrl: Property<String>
    abstract val repoUrl: Property<String>
    abstract val vcsUrl: Property<String>

    abstract val developerId: Property<String>
    abstract val developerName: Property<String>
    abstract val developerEmail: Property<String>

    abstract val licenseName: Property<String>
    abstract val licenseUrl: Property<String>

    abstract val propertiesFile: Property<String>
}