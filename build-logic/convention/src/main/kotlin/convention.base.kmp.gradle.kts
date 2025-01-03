import org.alexcawl.plugins.jvm.desktopConfiguration
import org.alexcawl.plugins.kotlin.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.kotlinJvmToolchainVersionValue
import org.alexcawl.plugins.projectVersionNameValue
import org.alexcawl.plugins.toJavaLanguageVersion
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

kotlinMultiplatformConfiguration {
    jvmToolchain {
        languageVersion.set(kotlinJvmToolchainVersionValue.toJavaLanguageVersion())
    }
}

desktopConfiguration {
    application {
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageVersion = projectVersionNameValue
        }
    }
}
