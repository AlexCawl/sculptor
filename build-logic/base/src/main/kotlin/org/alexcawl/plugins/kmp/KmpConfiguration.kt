package org.alexcawl.plugins.kmp

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

inline fun Project.kotlinMultiplatformConfiguration(
    block: KotlinMultiplatformExtension.() -> Unit
): Unit = try {
    block(kotlinMultiplatformExtension)
} catch (ignored: IllegalStateException) {
    project.logger.warn("Kotlin Multiplatform is not applied for ${project.name}")
}
