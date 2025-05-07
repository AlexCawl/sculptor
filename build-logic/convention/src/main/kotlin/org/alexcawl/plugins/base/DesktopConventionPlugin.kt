package org.alexcawl.plugins.base

import org.alexcawl.plugins.BaseConventionPlugin
import org.alexcawl.plugins.desktop.desktopConfiguration
import org.alexcawl.plugins.kmp.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.projectVersionNameValue
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

internal class DesktopConventionPlugin : BaseConventionPlugin() {
    override fun Project.configure() {
        plugins.apply(type = JvmConventionPlugin::class)
        desktopConfiguration {
            application {
                nativeDistributions {
                    targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                    packageVersion = projectVersionNameValue
                }
            }
        }
        kotlinMultiplatformConfiguration {
            jvm()
        }
    }
}
