import org.alexcawl.plugins.kotlin.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.libs
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("io.gitlab.arturbosch.detekt")

    id("convention.base.common")
    id("convention.base.android")
    id("convention.base.kmp")
    id("convention.base.detekt")
}

// Workaround for problem with skiko native distribution
// https://github.com/JetBrains/compose-multiplatform/issues/3123
private val skikoNativeDistribution: String
    get() {
        val osName = System.getProperty("os.name")
        val targetOs = when {
            osName == "Mac OS X" -> "macos"
            osName.startsWith("Win") -> "windows"
            osName.startsWith("Linux") -> "linux"
            else -> error("Unsupported OS: $osName")
        }
        val targetArch = when (val osArch = System.getProperty("os.arch")) {
            "x86_64", "amd64" -> "x64"
            "aarch64" -> "arm64"
            else -> error("Unsupported arch: $osArch")
        }
        val version: String = libs.versions.version.common.skiko.get()
        val target = "${targetOs}-${targetArch}"
        return "org.jetbrains.skiko:skiko-awt-runtime-$target:$version"
    }

kotlinMultiplatformConfiguration {
    androidTarget()
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.bundles.common.source)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.common.test)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.bundles.android.source)
                implementation(compose.uiTooling)
            }
        }

        androidInstrumentedTest {
            dependencies {
                implementation(libs.bundles.android.test)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.bundles.jvm.source)
                implementation(skikoNativeDistribution)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.bundles.jvm.test)
            }
        }
    }
}
