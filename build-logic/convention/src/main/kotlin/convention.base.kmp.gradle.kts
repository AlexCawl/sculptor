import org.alexcawl.plugins.kotlin.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.kotlinJvmToolchainVersionValue
import org.alexcawl.plugins.toJavaLanguageVersion

kotlinMultiplatformConfiguration {
    explicitApi()

    jvm()
    androidTarget()

    jvmToolchain {
        languageVersion.set(kotlinJvmToolchainVersionValue.toJavaLanguageVersion())
    }
}
