package org.alexcawl.plugins.base.plugins.platform

import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.configurations.javaCompilerConfiguration
import org.alexcawl.plugins.base.configurations.kotlinCompilerConfiguration
import org.alexcawl.plugins.base.javaCompileTargetVersionValue
import org.alexcawl.plugins.base.kotlinLanguageVersionValue
import org.alexcawl.plugins.base.toJvmTarget
import org.alexcawl.plugins.base.toKotlinVersion
import org.gradle.api.Project

@InternalBuildLogic
internal class JvmPlatformPlugin : PlatformPlugin() {
    override fun Project.configure() {
        kotlinCompilerConfiguration {
            jvmTarget.set(javaCompileTargetVersionValue.toJvmTarget())
            languageVersion.set(kotlinLanguageVersionValue.toKotlinVersion())
        }
        javaCompilerConfiguration {
            sourceCompatibility = javaCompileTargetVersionValue
            targetCompatibility = javaCompileTargetVersionValue
        }
    }
}
