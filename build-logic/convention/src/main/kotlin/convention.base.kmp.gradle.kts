import org.alexcawl.plugins.kotlin.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.kotlinJvmToolchainVersionValue
import org.alexcawl.plugins.toJavaLanguageVersion

kotlinMultiplatformConfiguration {
    jvm()
    androidTarget()

    jvmToolchain {
        languageVersion.set(kotlinJvmToolchainVersionValue.toJavaLanguageVersion())
    }
}
