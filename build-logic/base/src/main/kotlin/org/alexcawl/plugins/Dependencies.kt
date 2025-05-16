package org.alexcawl.plugins

import org.alexcawl.plugins.kmp.kotlinMultiplatformConfiguration
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

fun DependencyHandlerScope.implementation(
    dependency: Any
) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.api(
    dependency: Any
) {
    add("api", dependency)
}

fun DependencyHandlerScope.testImplementation(
    dependency: Any
) {
    add("testImplementation", dependency)
}

fun Project.commonMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.commonMain.dependencies(block)
    }
}

fun Project.androidMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.androidMain.dependencies(block)
    }
}

fun Project.jvmMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.jvmMain.dependencies(block)
    }
}

fun Project.wasmJsMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    kotlinMultiplatformConfiguration {
        sourceSets.wasmJsMain.dependencies(block)
    }
}
