package org.alexcawl.plugins.base

import org.alexcawl.plugins.BaseConventionPlugin
import org.alexcawl.plugins.kmp.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.wasmJs.wasmJsConfiguration
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.DevServer

internal class WasmJsConventionPlugin : BaseConventionPlugin() {
    override fun Project.configure() {
        wasmJsConfiguration {
            browser {
                commonWebpackConfig {
                    outputFileName = "client.js"
                    devServer = (devServer ?: DevServer()).apply {
                        port = 8082
                        static = (static ?: mutableListOf()).apply {
                            // Serve sources to debug inside browser
                            add(project.rootDir.path)
                            add(project.projectDir.path)
                        }
                    }
                }
                testTask {
                    useKarma {
                        useChromeHeadless()
                        useChromium()
                    }
                }
                binaries.executable()
            }
        }
        kotlinMultiplatformConfiguration {
            @OptIn(ExperimentalWasmDsl::class)
            wasmJs {
                browser()
            }
        }
    }
}
