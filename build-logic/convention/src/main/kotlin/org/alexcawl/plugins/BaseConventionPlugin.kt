package org.alexcawl.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BaseConventionPlugin : Plugin<Project> {
    final override fun apply(target: Project): Unit = target.configure()

    protected abstract fun Project.configure()
}
