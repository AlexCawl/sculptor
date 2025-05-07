package org.alexcawl.plugins.project

import gradle.kotlin.dsl.accessors._3129fc1ae8dace3c3c4f34f6b035e767.compose
import org.alexcawl.plugins.kmp.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.libs
import org.gradle.api.Project

class ShowroomModulePlugin : CmpModulePlugin() {
    override val isApplicationModule: Boolean = true

    override fun Project.configure() {
        super.apply(target = this)

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
