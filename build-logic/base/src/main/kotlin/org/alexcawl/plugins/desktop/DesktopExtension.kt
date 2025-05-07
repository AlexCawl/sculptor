package org.alexcawl.plugins.desktop

import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension

@PublishedApi
internal val Project.desktopExtension: DesktopExtension
    get() = extensions.findByType<ComposeExtension>()
        ?.extensions?.findByType<DesktopExtension>()
        ?: error(
            """
                "Project.desktopExtension" value may be called only from KMP application 
                or KMP library gradle script!
                Check if id("org.jetbrains.kotlin.multiplatform") was applied!
            """.trimIndent()
        )
