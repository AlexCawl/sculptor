package org.alexcawl.plugins.project

import org.alexcawl.plugins.BaseConventionPlugin
import org.alexcawl.plugins.base.JvmConventionPlugin
import org.alexcawl.plugins.implementation
import org.alexcawl.plugins.libs
import org.alexcawl.plugins.testImplementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class JvmModulePlugin : BaseConventionPlugin() {
    override fun Project.configure() {
        with(plugins) {
            apply(type = JvmConventionPlugin::class)
        }
        dependencies {
            implementation(libs.bundles.common.source)
            testImplementation(libs.bundles.common.test)
            implementation(libs.bundles.jvm)
            testImplementation(libs.bundles.jvm.test)
        }
    }
}
