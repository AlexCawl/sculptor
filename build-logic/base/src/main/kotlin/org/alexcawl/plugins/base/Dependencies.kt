package org.alexcawl.plugins.base

import org.alexcawl.plugins.base.configurations.kotlinMultiplatformConfiguration
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import java.util.Optional

public fun Project.commonMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.commonMain.dependencies(block)
    }
}

public fun Project.commonTestDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.commonTest.dependencies(block)
    }
}

public fun Project.androidMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.androidMain.dependencies(block)
    }
}

public fun Project.androidTestDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.androidInstrumentedTest.dependencies(block)
    }
}

public fun Project.jvmMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.jvmMain.dependencies(block)
    }
}

public fun Project.jvmTestDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.jvmTest.dependencies(block)
    }
}

public fun Project.wasmJsMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.wasmJsMain.dependencies(block)
    }
}

public fun Project.wasmJsTestDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.wasmJsTest.dependencies(block)
    }
}
