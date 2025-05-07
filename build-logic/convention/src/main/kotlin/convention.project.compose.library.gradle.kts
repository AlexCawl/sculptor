import org.alexcawl.plugins.libs
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("com.android.library")

    id("org.jetbrains.kotlin.multiplatform") // Kotlin multiplatform
    id("org.jetbrains.kotlin.plugin.serialization") // Kotlin serialization
    id("org.jetbrains.compose") // Compose multiplatform
    id("org.jetbrains.kotlin.plugin.compose") // Compose compiler
    id("io.gitlab.arturbosch.detekt") // Detekt

    id("convention.base.common")
    id("convention.base.android")
    id("convention.base.desktop")
    id("convention.base.wasmJs")
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

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.version.common.kotlin.language.get()
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // Bundle
                implementation(libs.bundles.common.source)

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

        commonTest {
            dependencies {
                // Bundle
                implementation(libs.bundles.common.test)

                // Compose
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)
            }
        }

        androidMain {
            dependencies {
                // Bundle
                implementation(libs.bundles.android.source)

                // Compose
                implementation(compose.uiTooling)

                // Android
                implementation(libs.android.activityCompose)
            }
        }

        androidInstrumentedTest {
            dependencies {
                // Bundle
                implementation(libs.bundles.android.test)
            }
        }

        jvmMain {
            dependencies {
                // Bundle
                implementation(libs.bundles.jvm.source)

                // Compose
                implementation(compose.desktop.common)
                implementation(skikoNativeDistribution)
            }
        }

        jvmTest {
            dependencies {
                // Bundle
                implementation(libs.bundles.jvm.test)
            }
        }
    }
}
