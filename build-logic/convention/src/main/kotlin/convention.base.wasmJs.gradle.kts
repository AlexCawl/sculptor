import org.alexcawl.plugins.wasmJs.wasmJsConfiguration
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.DevServer

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
