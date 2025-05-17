package org.alexcawl.plugins.base.plugins.platform

import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.configurations.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.base.configurations.wasmJsConfiguration
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.DevServer

@InternalBuildLogic
internal class WasmJsPlatformPlugin : PlatformPlugin() {
    override fun Project.configure() {
        wasmJsConfiguration {
            browser {
                commonWebpackConfig {
                    outputFileName = OUTPUT_FILE_NAME
                    devServer = (devServer ?: DevServer()).apply {
                        port = DEV_SERVER_PORT
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

    private companion object {
        private const val OUTPUT_FILE_NAME = "client.js"
        private const val DEV_SERVER_PORT = 8082
    }
}
