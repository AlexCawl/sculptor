import org.alexcawl.plugins.libs
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("com.android.application")

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
                implementation(libs.bundles.common.source)
                implementation(compose.foundation)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.common.lifecycle.compose)
                implementation(libs.common.lifecycle.viewmodel)
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
                implementation(libs.android.activityCompose)
                implementation(libs.android.splash)
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
                implementation(compose.desktop.currentOs)
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
