package org.alexcawl.plugins.jvm

import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

inline fun Project.jvmConfiguration(
    block: KotlinJvmExtension.() -> Unit
): Unit = try {
    block(jvmExtension)
} catch (e: IllegalStateException) {
    project.logger.warn("Jvm is not applied for ${project.name}")
}

inline fun Project.kotlinCompilerConfiguration(
    crossinline block: KotlinJvmCompilerOptions.() -> Unit
) {
    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            block()
        }
    }
}

inline fun Project.javaCompilerConfiguration(
    crossinline block: JavaCompile.() -> Unit
) {
    tasks.withType<JavaCompile>().configureEach {
        block(this)
    }
}
