package dev.sarquella.plugin.extensions

import org.gradle.api.Action
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

abstract class PublicationParamsExtension {
    abstract val groupId: Property<String>
    abstract val artifact: Property<String>
    abstract val version: Property<String>

    abstract val description: Property<String>

    abstract val webUrl: Property<String>
    abstract val repoUrl: Property<String>
    abstract val vcsUrl: Property<String>

    @get:Nested
    abstract val developer: Developer

    @Suppress("unused")
    fun developer(action: Action<in Developer>) {
        action.execute(developer)
    }

    @get:Nested
    abstract val license: License

    @Suppress("unused")
    fun license(action: Action<in License>) {
        action.execute(license)
    }

    abstract val propertiesFile: Property<String>

    abstract class Developer {
        abstract val id: Property<String>
        abstract val name: Property<String>
        abstract val email: Property<String>
    }

    abstract class License {
        abstract val name: Property<String>
        abstract val url: Property<String>
    }
}