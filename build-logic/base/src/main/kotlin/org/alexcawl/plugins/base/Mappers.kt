package org.alexcawl.plugins.base

import org.gradle.api.JavaVersion
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

internal fun String.toJvmTarget(): JvmTarget {
    val version: String = when (this) {
        "8" -> "1_8"
        "9",
        "10",
        "11",
        "12",
        "13",
        "14",
        "15",
        "16",
        "17",
        "18",
        "19",
        "20",
        "21",
        "22",
        "23" -> this

        else -> error("Invalid java target version: $this")
    }
    return JvmTarget.fromTarget(target = version)
}

internal fun String.toKotlinVersion(): KotlinVersion {
    val (major: Int, minor: Int) = this.split(".").map(String::toInt).let { it[0] to it[1] }
    return KotlinVersion.fromVersion("$major.$minor")
}

internal fun String.toJavaVersion(): JavaVersion {
    val version: String = when (this) {
        "1" -> "1_1"
        "2" -> "1_2"
        "3" -> "1_3"
        "4" -> "1_4"
        "5" -> "1_5"
        "6" -> "1_6"
        "7" -> "1_7"
        "8" -> "1_8"
        "9",
        "10",
        "11",
        "12",
        "13",
        "14",
        "15",
        "16",
        "17",
        "18",
        "19",
        "20",
        "21",
        "22",
        "23",
        "24",
        "25",
        "26" -> this

        else -> error("Invalid java target version: $this")
    }
    return JavaVersion.valueOf(value = "VERSION_$version")
}

internal fun String.toJavaLanguageVersion(): JavaLanguageVersion {
    return JavaLanguageVersion.of(this.toInt())
}
