package org.alexcawl.plugins.base.plugins.platform

import org.alexcawl.plugins.base.InternalBuildLogic
import org.gradle.api.Plugin
import org.gradle.api.Project

@InternalBuildLogic
internal abstract class PlatformPlugin : Plugin<Project> {
    final override fun apply(target: Project): Unit = target.configure()

    protected abstract fun Project.configure()
}
