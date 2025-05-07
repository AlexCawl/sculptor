package org.alexcawl.plugins

import org.gradle.api.Project
import org.jetbrains.compose.ComposePlugin

@PublishedApi
internal val Project.compose: ComposePlugin.Dependencies
    get() = extensions.getByName("compose") as ComposePlugin.Dependencies
