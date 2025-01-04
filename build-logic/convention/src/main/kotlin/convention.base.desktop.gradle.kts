import org.alexcawl.plugins.jvm.desktopConfiguration
import org.alexcawl.plugins.projectVersionNameValue
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

desktopConfiguration {
    application {
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageVersion = projectVersionNameValue
        }
    }
}
