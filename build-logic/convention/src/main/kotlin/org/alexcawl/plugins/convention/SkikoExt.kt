package org.alexcawl.plugins.convention

import org.alexcawl.plugins.base.libs
import org.gradle.api.Project

// Workaround for problem with skiko native distribution
// https://github.com/JetBrains/compose-multiplatform/issues/3123
internal val Project.skikoNativeDistribution: String
    get() {
        val osName = System.getProperty("os.name")
        val targetOs = when {
            osName == "Mac OS X" -> "macos"
            osName.startsWith("Win") -> "windows"
            osName.startsWith("Linux") -> "linux"
            else -> error("Unsupported OS: $osName")
        }
        val targetArch = when (val osArch = System.getProperty("os.arch")) {
            "x86_64", "amd64" -> "x64"
            "aarch64" -> "arm64"
            else -> error("Unsupported arch: $osArch")
        }
        val version: String = libs.versions.version.common.skiko.get()
        val target = "${targetOs}-${targetArch}"
        return "org.jetbrains.skiko:skiko-awt-runtime-$target:$version"
    }
