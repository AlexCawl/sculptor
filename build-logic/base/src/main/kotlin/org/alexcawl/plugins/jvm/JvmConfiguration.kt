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
) = block(jvmExtension)

inline fun Project.desktopConfiguration(
    block: DesktopExtension.() -> Unit
) = try {
    block(desktopExtension)
} catch (ignored: IllegalStateException) {
    // no-op
}

fun Project.desktop(
    applicationClass: String,
    applicationPackage: String,
    block: DesktopExtension.() -> Unit = {}
) {
    desktopConfiguration {
        application {
            mainClass = applicationClass
            nativeDistributions {
                packageName = applicationPackage
            }
        }
        block()
    }
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
