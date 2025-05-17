package org.alexcawl.plugins.base.configurations

import org.alexcawl.plugins.base.extensions.kotlinMultiplatformExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

public inline fun Project.kotlinMultiplatformConfiguration(
    block: KotlinMultiplatformExtension.() -> Unit
): Unit = try {
    block(kotlinMultiplatformExtension)
} catch (ignored: IllegalStateException) {
    project.logger.warn("Kotlin Multiplatform is not applied for ${project.name}")
}
