package org.alexcawl.plugins.project

import org.alexcawl.plugins.BaseConventionPlugin
import org.alexcawl.plugins.compose
import org.alexcawl.plugins.kmp.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class ShowroomModulePlugin : BaseConventionPlugin() {
    override fun Project.configure() {
        with(plugins) {
            apply(CmpModuleApplicationPlugin::class)
        }
        kotlinMultiplatformConfiguration {
            sourceSets.androidMain {
                dependencies {
                    // Compose
                    implementation(libs.android.activityCompose)
                }
            }
            sourceSets.jvmMain {
                dependencies {
                    // Compose
                    implementation(compose.desktop.currentOs)
                }
            }
        }
    }
}
