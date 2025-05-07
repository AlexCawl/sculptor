package org.alexcawl.plugins.project

import org.alexcawl.plugins.BaseConventionPlugin
import org.alexcawl.plugins.android.androidBaseConfiguration
import org.alexcawl.plugins.compose
import org.alexcawl.plugins.kmp.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.libs
import org.alexcawl.plugins.skikoNativeDistribution
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.compose.ExperimentalComposeLibrary

sealed class CmpModulePlugin : BaseConventionPlugin() {
    abstract val isApplicationModule: Boolean

    override fun Project.configure() {
        with(plugins) {
            apply("org.jetbrains.compose") // Compose multiplatform
            apply("org.jetbrains.kotlin.plugin.compose") // Compose compiler

            if (isApplicationModule) {
                apply(KmpModuleApplicationPlugin::class)
            } else {
                apply(KmpModuleLibraryPlugin::class)
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

        kotlinMultiplatformConfiguration {
            sourceSets.commonMain {
                dependencies {
                    // Compose
                    implementation(compose.foundation)
                    implementation(compose.components.resources)
                    implementation(compose.components.uiToolingPreview)

                    // Lifecycle
                    implementation(libs.common.lifecycle.compose)
                    implementation(libs.common.lifecycle.viewmodel)
                    implementation(libs.common.lifecycle.runtime)
                }
            }

            sourceSets.commonTest {
                dependencies {
                    // Compose
                    @OptIn(ExperimentalComposeLibrary::class)
                    implementation(compose.uiTest)
                }
            }

            sourceSets.androidMain {
                dependencies {
                    // Compose
                    implementation(compose.uiTooling)

                    // Android
                    implementation(libs.android.activityCompose)
                }
            }

            sourceSets.jvmMain {
                dependencies {
                    // Compose
                    implementation(compose.desktop.common)
                    implementation(skikoNativeDistribution)
                }
            }
        }
    }
}

class CmpModuleLibraryPlugin : CmpModulePlugin() {
    override val isApplicationModule: Boolean = false
}

internal class CmpModuleApplicationPlugin : CmpModulePlugin() {
    override val isApplicationModule: Boolean = true
}
