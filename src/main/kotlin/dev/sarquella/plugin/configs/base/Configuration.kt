package dev.sarquella.plugin.configs.base

import org.gradle.api.Project

interface Configuration {
    fun configure(project: Project)
}