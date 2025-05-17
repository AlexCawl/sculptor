package org.alexcawl.plugins.base.configurations

import org.alexcawl.plugins.base.extensions.desktopExtension
import org.gradle.api.Project
import org.jetbrains.compose.desktop.DesktopExtension

public inline fun Project.desktopConfiguration(
    block: DesktopExtension.() -> Unit
): Unit = try {
    block(desktopExtension)
} catch (ignored: IllegalStateException) {
    project.logger.warn("Compose desktop is not applied for ${project.name}")
}

public fun Project.desktop(
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
