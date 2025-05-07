package org.alexcawl.plugins.project

import org.alexcawl.plugins.BaseConventionPlugin
import org.alexcawl.plugins.base.JvmConventionPlugin
import org.alexcawl.plugins.implementation
import org.alexcawl.plugins.javaCompileTargetVersionValue
import org.alexcawl.plugins.jvm.jvmConfiguration
import org.alexcawl.plugins.kotlinJvmToolchainVersionValue
import org.alexcawl.plugins.libs
import org.alexcawl.plugins.testImplementation
import org.alexcawl.plugins.toJavaLanguageVersion
import org.alexcawl.plugins.toJvmTarget
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ServerModulePlugin : BaseConventionPlugin() {
    override fun Project.configure() {
        with(plugins) {
            apply("org.jetbrains.kotlin.jvm")
            apply(type = JvmConventionPlugin::class)
        }

        jvmConfiguration {
            compilerOptions {
                jvmTarget.set(javaCompileTargetVersionValue.toJvmTarget())
            }
            jvmToolchain {
                languageVersion.set(kotlinJvmToolchainVersionValue.toJavaLanguageVersion())
            }
        }

        dependencies {
            implementation(libs.bundles.common.source)
            testImplementation(libs.bundles.common.test)
            implementation(libs.bundles.jvm)
            testImplementation(libs.bundles.jvm.test)
        }
    }
}
