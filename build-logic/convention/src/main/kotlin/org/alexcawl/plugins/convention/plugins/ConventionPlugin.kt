package org.alexcawl.plugins.convention.plugins

import org.alexcawl.plugins.base.InternalBuildLogic
import org.gradle.api.Plugin
import org.gradle.api.Project

@InternalBuildLogic
public abstract class ConventionPlugin : Plugin<Project> {
    protected abstract val isApplication: Boolean

    final override fun apply(target: Project): Unit = target.configure()

    protected abstract fun Project.configure()
}
