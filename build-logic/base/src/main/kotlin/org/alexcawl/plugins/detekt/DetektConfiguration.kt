package org.alexcawl.plugins.detekt

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import java.io.File

inline fun Project.detektConfiguration(
    block: DetektExtension.(DetektConfiguration) -> Unit
) = block(detektExtension, DetektConfigurationImpl)

interface DetektConfiguration {
    val Project.detektConfigPathValue: String
}

@PublishedApi
internal object DetektConfigurationImpl : DetektConfiguration {
    override val Project.detektConfigPathValue: String
        get() = File(rootDir.absolutePath, "detekt.yml").absolutePath
}
