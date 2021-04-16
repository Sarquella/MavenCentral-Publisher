package dev.sarquella.plugin.helper.extensions

import org.gradle.api.Task

fun <T: Task> Class<T>.taskName() = simpleName.decapitalize()