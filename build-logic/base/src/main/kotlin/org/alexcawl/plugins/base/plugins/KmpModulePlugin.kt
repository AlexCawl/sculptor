package org.alexcawl.plugins.base.plugins

import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.configurations.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.base.plugins.platform.AndroidPlatformPlugin
import org.alexcawl.plugins.base.plugins.platform.DesktopPlatformPlugin
import org.alexcawl.plugins.base.plugins.platform.DetektUtilityPlugin
import org.alexcawl.plugins.base.plugins.platform.WasmJsPlatformPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

@InternalBuildLogic
public sealed class KmpModulePlugin : ModulePlugin() {
    override fun Project.configure() {
        with(plugins) {
            apply(KOTLIN_MULTIPLATFORM_PLUGIN_ID)
            when (isApplication) {
                true -> apply(ANDROID_APPLICATION_PLUGIN_ID)
                false -> apply(ANDROID_LIBRARY_PLUGIN_ID)
            }
            apply(type = AndroidPlatformPlugin::class)
            apply(type = WasmJsPlatformPlugin::class)
            apply(type = DesktopPlatformPlugin::class)
            apply(type = DetektUtilityPlugin::class)
        }

        kotlinMultiplatformConfiguration {
            explicitApi()
        }
    }

    private companion object {
        private const val KOTLIN_MULTIPLATFORM_PLUGIN_ID = "org.jetbrains.kotlin.multiplatform"
        private const val ANDROID_APPLICATION_PLUGIN_ID = "com.android.application"
        private const val ANDROID_LIBRARY_PLUGIN_ID = "com.android.library"
    }
}

@OptIn(InternalBuildLogic::class)
public class KmpLibrary : KmpModulePlugin() {
    override val isApplication: Boolean = false
}

@OptIn(InternalBuildLogic::class)
public class KmpApplication : KmpModulePlugin() {
    override val isApplication: Boolean = true
}
