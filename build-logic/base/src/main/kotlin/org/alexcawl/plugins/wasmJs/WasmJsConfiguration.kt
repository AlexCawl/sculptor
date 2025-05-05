package org.alexcawl.plugins.wasmJs

import org.alexcawl.plugins.kotlin.kotlinMultiplatformExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinWasmJsTargetDsl

@OptIn(ExperimentalWasmDsl::class)
fun Project.wasmJsConfiguration(action: KotlinWasmJsTargetDsl.() -> Unit) {
    kotlinMultiplatformExtension.wasmJs(action)
}
