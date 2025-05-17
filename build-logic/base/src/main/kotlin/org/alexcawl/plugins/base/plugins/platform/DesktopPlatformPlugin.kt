package org.alexcawl.plugins.base.plugins.platform

import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.configurations.kotlinMultiplatformConfiguration
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

@InternalBuildLogic
internal class DesktopPlatformPlugin : PlatformPlugin() {
    override fun Project.configure() {
        with(plugins) {
            apply(type = JvmPlatformPlugin::class)
        }

        kotlinMultiplatformConfiguration {
            jvm()
        }
    }
}
