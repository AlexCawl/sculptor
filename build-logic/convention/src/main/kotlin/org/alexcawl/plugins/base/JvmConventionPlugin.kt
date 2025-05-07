package org.alexcawl.plugins.base

import org.alexcawl.plugins.BaseConventionPlugin
import org.alexcawl.plugins.javaCompileTargetVersionValue
import org.alexcawl.plugins.jvm.javaCompilerConfiguration
import org.alexcawl.plugins.jvm.jvmConfiguration
import org.alexcawl.plugins.jvm.kotlinCompilerConfiguration
import org.alexcawl.plugins.kotlinJvmToolchainVersionValue
import org.alexcawl.plugins.kotlinLanguageVersionValue
import org.alexcawl.plugins.toJavaLanguageVersion
import org.alexcawl.plugins.toJvmTarget
import org.alexcawl.plugins.toKotlinVersion
import org.gradle.api.Project

internal class JvmConventionPlugin : BaseConventionPlugin() {
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
