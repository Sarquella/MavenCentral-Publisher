package dev.sarquella.plugin.helper.extensions

import com.android.build.gradle.BaseExtension
import dev.sarquella.plugin.PublisherPlugin
import dev.sarquella.plugin.extensions.PublicationParamsExtension
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.publish.PublishingExtension
import org.gradle.plugins.signing.SigningExtension

val Project.isAndroidLibrary: Boolean
    get() = plugins.hasPlugin("com.android.library")

val Project.params: PublicationParamsExtension
    get() = extensions.getByType(PublicationParamsExtension::class.java)

val Project.publishing: PublishingExtension
    get() = extensions.getByType(PublishingExtension::class.java)

val Project.signing: SigningExtension
    get() = extensions.getByType(SigningExtension::class.java)

val Project.android: BaseExtension
    get() = extensions.getByType(BaseExtension::class.java)

fun <T> Project.createExtension(name: String, extension: Class<T>) {
    extensions.create(name, extension)
}

fun <T> Project.applyPlugin(plugin: Class<T>) {
    pluginManager.apply(plugin)
}

fun <T: Task> Project.registerTask(task: Class<T>) {
    tasks.register(task.taskName(), task).apply {
        get().group = PublisherPlugin.PLUGIN_GROUP
    }
}