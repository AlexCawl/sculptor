package org.alexcawl.plugins.base.extensions

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

@PublishedApi
internal val Project.detektExtension: DetektExtension
    get() = extensions.findByType(DetektExtension::class)
        ?: error(
            """
                Check if id("io.gitlab.arturbosch.detekt") was applied!
            """.trimIndent()
        )
