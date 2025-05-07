package org.alexcawl.plugins.desktop

import org.gradle.api.Project
import org.jetbrains.compose.desktop.DesktopExtension

inline fun Project.desktopConfiguration(
    block: DesktopExtension.() -> Unit
): Unit = try {
    block(desktopExtension)
} catch (ignored: IllegalStateException) {
    project.logger.warn("Compose desktop is not applied for ${project.name}")
}

fun Project.desktop(
    applicationClass: String,
    applicationPackage: String,
    block: DesktopExtension.() -> Unit = {}
) {
    desktopConfiguration {
        application {
            mainClass = applicationClass
            nativeDistributions {
                packageName = applicationPackage
            }
        }
        block()
    }
}
