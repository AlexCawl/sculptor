package org.alexcawl.plugins.detekt

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import java.io.File

inline fun Project.detektConfiguration(
    block: DetektExtension.(DetektConfiguration) -> Unit,
): Unit = try {
    block(detektExtension, DetektConfigurationImpl)
} catch (ignored: IllegalStateException) {
    project.logger.warn("Detekt is not applied for ${project.name}")
}

interface DetektConfiguration {
    val Project.detektConfigPathValue: String
}

@PublishedApi
internal object DetektConfigurationImpl : DetektConfiguration {
    override val Project.detektConfigPathValue: String
        get() = File(rootDir.absolutePath, "detekt.yml").absolutePath
}
