@file:Suppress("unused")

package org.alexcawl.plugins.base.plugins

import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.configurations.jvmConfiguration
import org.alexcawl.plugins.base.javaCompileTargetVersionValue
import org.alexcawl.plugins.base.kotlinJvmToolchainVersionValue
import org.alexcawl.plugins.base.plugins.platform.DetektUtilityPlugin
import org.alexcawl.plugins.base.plugins.platform.JvmPlatformPlugin
import org.alexcawl.plugins.base.toJavaLanguageVersion
import org.alexcawl.plugins.base.toJvmTarget
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

@InternalBuildLogic
public sealed class ServerModulePlugin : ModulePlugin() {
    override fun Project.configure() {
        with(plugins) {
            apply(KOTLIN_JVM_PLUGIN_ID)
            apply(type = JvmPlatformPlugin::class)
            apply(type = DetektUtilityPlugin::class)
        }

        jvmConfiguration {
            compilerOptions {
                jvmTarget.set(javaCompileTargetVersionValue.toJvmTarget())
            }
            jvmToolchain {
                languageVersion.set(kotlinJvmToolchainVersionValue.toJavaLanguageVersion())
            }
        }
    }

    private companion object {
        const val KOTLIN_JVM_PLUGIN_ID = "org.jetbrains.kotlin.jvm"
    }
}

@OptIn(InternalBuildLogic::class)
public class ServerLibrary : ServerModulePlugin() {
    override val isApplication: Boolean = false
}

@OptIn(InternalBuildLogic::class)
public class ServerApplication : ServerModulePlugin() {
    override val isApplication: Boolean = true
}