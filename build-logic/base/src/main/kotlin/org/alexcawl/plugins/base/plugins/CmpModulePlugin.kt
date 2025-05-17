package org.alexcawl.plugins.base.plugins

import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.configurations.androidBaseConfiguration
import org.alexcawl.plugins.base.configurations.desktopConfiguration
import org.alexcawl.plugins.base.libs
import org.alexcawl.plugins.base.projectVersionNameValue
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

@InternalBuildLogic
public sealed class CmpModulePlugin : ModulePlugin() {
    override fun Project.configure() {
        with(plugins) {
            apply(COMPOSE_MULTIPLATFORM_PLUGIN_ID)
            apply(COMPOSE_COMPILER_PLUGIN_ID)
            when (isApplication) {
                true -> apply(type = KmpApplication::class)
                false -> apply(type = KmpLibrary::class)
            }
        }
        androidBaseConfiguration {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = libs.versions.version.common.kotlin.language.get()
            }
        }
        if (isApplication) {
            desktopConfiguration {
                application {
                    nativeDistributions {
                        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                        packageVersion = projectVersionNameValue
                    }
                }
            }
        }
    }

    private companion object {
        private const val COMPOSE_MULTIPLATFORM_PLUGIN_ID = "org.jetbrains.compose"
        private const val COMPOSE_COMPILER_PLUGIN_ID = "org.jetbrains.kotlin.plugin.compose"
    }
}

@OptIn(InternalBuildLogic::class)
public class CmpLibrary : CmpModulePlugin() {
    override val isApplication: Boolean = false
}

@OptIn(InternalBuildLogic::class)
public class CmpApplication : CmpModulePlugin() {
    override val isApplication: Boolean = true
}
