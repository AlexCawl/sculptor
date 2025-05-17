package org.alexcawl.plugins.base.configurations

import org.alexcawl.plugins.base.extensions.jvmExtension
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

public inline fun Project.jvmConfiguration(
    block: KotlinJvmExtension.() -> Unit
): Unit = try {
    block(jvmExtension)
} catch (e: IllegalStateException) {
    project.logger.warn("Jvm is not applied for ${project.name}")
}

public inline fun Project.kotlinCompilerConfiguration(
    crossinline block: KotlinJvmCompilerOptions.() -> Unit
) {
    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            block()
        }
    }
}

public inline fun Project.javaCompilerConfiguration(
    crossinline block: JavaCompile.() -> Unit
) {
    tasks.withType<JavaCompile>().configureEach {
        block(this)
    }
}
