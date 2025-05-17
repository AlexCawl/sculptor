package org.alexcawl.plugins.base.plugins.platform

import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.configurations.DetektConfigurationImpl.detektConfigPathValue
import org.alexcawl.plugins.base.configurations.detektConfiguration
import org.gradle.api.Project

@InternalBuildLogic
internal class DetektUtilityPlugin : PlatformPlugin() {
    override fun Project.configure() {
        with(plugins) {
            apply(DETEKT_PLUGIN_ID)
        }
        detektConfiguration {
            config.setFrom(detektConfigPathValue)
        }
    }

    private companion object {
        private const val DETEKT_PLUGIN_ID = "io.gitlab.arturbosch.detekt"
    }
}
