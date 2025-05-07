package org.alexcawl.plugins.kmp

import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@PublishedApi
internal val Project.kotlinMultiplatformExtension: KotlinMultiplatformExtension
    get() = extensions.findByType<KotlinMultiplatformExtension>()
        ?: error(
            """
                "Project.kotlinMultiplatformExtension" value may be called only from KMP application 
                or KMP library gradle script!
                Check if id("org.jetbrains.kotlin.multiplatform") was applied!
            """.trimIndent()
        )
