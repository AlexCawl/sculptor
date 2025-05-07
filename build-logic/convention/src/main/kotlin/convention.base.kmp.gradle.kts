import org.alexcawl.plugins.kmp.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.kotlinJvmToolchainVersionValue
import org.alexcawl.plugins.toJavaLanguageVersion
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

kotlinMultiplatformConfiguration {
    explicitApi()

    // Desktop
    jvm()
    jvmToolchain {
        languageVersion.set(kotlinJvmToolchainVersionValue.toJavaLanguageVersion())
    }

    // Android
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
    }

    // WasmJs
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }
}
