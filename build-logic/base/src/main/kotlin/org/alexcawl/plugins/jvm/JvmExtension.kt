package org.alexcawl.plugins.jvm

import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension

@PublishedApi
internal val Project.jvmExtension: KotlinJvmExtension
    get() = extensions.findByType(KotlinJvmExtension::class)
        ?: error(
            """
                "Project.jvmExtension" value may be called only from jvm application gradle script.
                Check if id("org.jetbrains.kotlin.jvm") was applied!
            """.trimIndent()
        )
