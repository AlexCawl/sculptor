package org.alexcawl.plugins.base.configurations

import org.alexcawl.plugins.base.extensions.kotlinMultiplatformExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinWasmJsTargetDsl

@OptIn(ExperimentalWasmDsl::class)
public fun Project.wasmJsConfiguration(action: KotlinWasmJsTargetDsl.() -> Unit) {
    kotlinMultiplatformExtension.wasmJs(action)
}
