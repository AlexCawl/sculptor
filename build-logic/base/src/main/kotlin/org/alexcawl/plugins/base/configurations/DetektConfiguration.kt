package org.alexcawl.plugins.base.configurations

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.alexcawl.plugins.base.extensions.detektExtension
import org.gradle.api.Project
import java.io.File

public inline fun Project.detektConfiguration(
    block: DetektExtension.(DetektConfiguration) -> Unit,
): Unit = try {
    block(detektExtension, DetektConfigurationImpl)
} catch (ignored: IllegalStateException) {
    project.logger.warn("Detekt is not applied for ${project.name}")
}

public interface DetektConfiguration {
    public val Project.detektConfigPathValue: String
}

@PublishedApi
internal object DetektConfigurationImpl : DetektConfiguration {
    override val Project.detektConfigPathValue: String
        get() = File(rootDir.absolutePath, "detekt.yml").absolutePath
}
