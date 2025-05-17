package org.alexcawl.plugins.convention.plugins

import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.androidMainDependencies
import org.alexcawl.plugins.base.androidTestDependencies
import org.alexcawl.plugins.base.commonMainDependencies
import org.alexcawl.plugins.base.commonTestDependencies
import org.alexcawl.plugins.base.configurations.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.base.jvmMainDependencies
import org.alexcawl.plugins.base.jvmTestDependencies
import org.alexcawl.plugins.base.libs
import org.alexcawl.plugins.base.plugins.KmpApplication
import org.alexcawl.plugins.base.plugins.KmpLibrary
import org.alexcawl.plugins.base.wasmJsMainDependencies
import org.alexcawl.plugins.base.wasmJsTestDependencies
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

@InternalBuildLogic
public sealed class KmpConventionPlugin : ConventionPlugin() {
    override fun Project.configure() {
        with(plugins) {
            when (isApplication) {
                true -> apply(type = KmpApplication::class)
                false -> apply(type = KmpLibrary::class)
            }
            apply(KOTLIN_SERIALIZATION_PLUGIN_ID)
        }

        commonMainDependencies {
            implementation(libs.bundles.common.kmp.source)
        }

        commonTestDependencies {
            implementation(libs.bundles.common.kmp.test)
        }

        androidMainDependencies {
            implementation(libs.bundles.android.kmp.source)
        }

        androidTestDependencies {
            implementation(libs.bundles.android.kmp.test)
        }

        jvmMainDependencies {
           implementation(libs.bundles.jvm.kmp.source)
        }

        jvmTestDependencies {
            implementation(libs.bundles.jvm.kmp.test)
        }

        wasmJsMainDependencies {
            implementation(libs.bundles.wasmJs.kmp.source)
        }

        wasmJsTestDependencies {
            implementation(libs.bundles.wasmJs.kmp.test)
        }
    }

    private companion object {
        private const val KOTLIN_SERIALIZATION_PLUGIN_ID = "org.jetbrains.kotlin.plugin.serialization"
    }
}

@OptIn(InternalBuildLogic::class)
public class KmpLibraryConvention : KmpConventionPlugin() {
    override val isApplication: Boolean = false
}

@OptIn(InternalBuildLogic::class)
public class KmpApplicationConvention : KmpConventionPlugin() {
    override val isApplication: Boolean = true
}
