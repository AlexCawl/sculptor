package org.alexcawl.plugins.convention.plugins

import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.libs
import org.alexcawl.plugins.base.plugins.ServerApplication
import org.alexcawl.plugins.base.plugins.ServerLibrary
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.plugins

@InternalBuildLogic
public sealed class ServerConventionPlugin : ConventionPlugin() {
    override fun Project.configure() {
        with(plugins) {
            when (isApplication) {
                true -> {
                    apply(plugin = "application")
                    apply(type = ServerApplication::class)
                }
                false -> {
                    apply(plugin = "java-library")
                    apply(type = ServerLibrary::class)
                }
            }
        }

        dependencies {
            "implementation"(libs.bundles.server.source)
            "testImplementation"(libs.bundles.server.test)
        }
    }
}

@OptIn(InternalBuildLogic::class)
public class ServerLibraryConvention : ServerConventionPlugin() {
    override val isApplication: Boolean = false
}

@OptIn(InternalBuildLogic::class)
public class ServerApplicationConvention : ServerConventionPlugin() {
    override val isApplication: Boolean = true
}
