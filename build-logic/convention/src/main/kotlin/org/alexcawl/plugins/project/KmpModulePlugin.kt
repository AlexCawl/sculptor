package org.alexcawl.plugins.project

import org.alexcawl.plugins.BaseConventionPlugin
import org.alexcawl.plugins.base.AndroidConventionPlugin
import org.alexcawl.plugins.base.DesktopConventionPlugin
import org.alexcawl.plugins.base.WasmJsConventionPlugin
import org.alexcawl.plugins.kmp.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

open class KmpModulePlugin : BaseConventionPlugin() {
    protected open val isApplicationModule: Boolean = false

    override fun Project.configure() {
        with(plugins) {
            // Android plugin
            if (isApplicationModule) {
                apply("com.android.application")
            } else {
                apply("com.android.library")
            }

            // Plugins
            apply("org.jetbrains.kotlin.multiplatform") // Kotlin multiplatform
            apply("org.jetbrains.kotlin.plugin.serialization") // Kotlin serialization
            apply("io.gitlab.arturbosch.detekt") // Detekt

            // Convention plugins
            apply(type = AndroidConventionPlugin::class)
            apply(type = DesktopConventionPlugin::class)
            apply(type = WasmJsConventionPlugin::class)
        }

        kotlinMultiplatformConfiguration {
            sourceSets.commonMain {
                dependencies {
                    implementation(libs.bundles.common.source)
                }
            }

            sourceSets.commonTest {
                dependencies {
                    implementation(libs.bundles.common.test)
                }
            }

            sourceSets.androidMain {
                dependencies {
                    implementation(libs.bundles.android.source)
                }
            }

            sourceSets.androidInstrumentedTest {
                dependencies {
                    implementation(libs.bundles.android.test)
                }
            }

            sourceSets.jvmMain {
                dependencies {
                    implementation(libs.bundles.jvm.source)
                }
            }

            sourceSets.jvmTest {
                dependencies {
                    implementation(libs.bundles.jvm.test)
                }
            }

            sourceSets.wasmJsMain {
                // No dependencies
            }

            sourceSets.wasmJsTest {
                // No dependencies
            }
        }
    }
}
