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
import org.alexcawl.plugins.base.plugins.CmpApplication
import org.alexcawl.plugins.base.plugins.CmpLibrary
import org.alexcawl.plugins.base.wasmJsMainDependencies
import org.alexcawl.plugins.base.wasmJsTestDependencies
import org.alexcawl.plugins.convention.compose
import org.alexcawl.plugins.convention.skikoNativeDistribution
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.compose.ExperimentalComposeLibrary

@InternalBuildLogic
public sealed class CmpConventionPlugin : ConventionPlugin() {
    override fun Project.configure() {
        with(plugins) {
            when (isApplication) {
                true -> {
                    apply(type = CmpApplication::class)
                    apply(type = KmpApplicationConvention::class)
                }

                false -> {
                    apply(type = CmpLibrary::class)
                    apply(type = KmpLibraryConvention::class)
                }
            }
        }

        commonMainDependencies {
            implementation(libs.bundles.common.cmp.source)
            implementation(compose.foundation)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }

        commonTestDependencies {
            implementation(libs.bundles.common.cmp.test)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        androidMainDependencies {
            implementation(libs.bundles.android.cmp.source)
            implementation(compose.uiTooling)
        }

        androidTestDependencies {
            implementation(libs.bundles.android.cmp.test)
        }

        jvmMainDependencies {
            implementation(libs.bundles.jvm.cmp.source)
            implementation(skikoNativeDistribution)
            when (isApplication) {
                true -> implementation(compose.desktop.currentOs)
                false -> implementation(compose.desktop.common)
            }
        }

        jvmTestDependencies {
            implementation(libs.bundles.jvm.cmp.test)
        }

        wasmJsMainDependencies {
            implementation(libs.bundles.wasmJs.cmp.source)
        }

        wasmJsTestDependencies {
            implementation(libs.bundles.wasmJs.cmp.test)
        }
    }
}

@OptIn(InternalBuildLogic::class)
public class CmpLibraryConvention : CmpConventionPlugin() {
    override val isApplication: Boolean = false
}

@OptIn(InternalBuildLogic::class)
public class CmpApplicationConvention : CmpConventionPlugin() {
    override val isApplication: Boolean = true
}
