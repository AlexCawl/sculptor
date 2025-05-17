package org.alexcawl.plugins.base

import org.gradle.api.Project

// Android properties
public val Project.compileSdkValue: Int
    get() = properties["compileSdk"]?.toString()?.toInt()
        ?: error("Field \"compileSdk\" not found in gradle.properties")

public val Project.targetSdkValue: Int
    get() = properties["targetSdk"]?.toString()?.toInt()
        ?: error("Field \"targetSdk\" not found in gradle.properties")

public val Project.minSdkValue: Int
    get() = properties["minSdk"]?.toString()?.toInt()
        ?: error("Field \"minSdk\" not found in gradle.properties")

public val Project.buildToolsVersionValue: String
    get() = properties["buildToolsVersion"]?.toString()
        ?: error("Field \"buildToolsVersion\" not found in gradle.properties")

public val Project.ndkVersionValue: String
    get() = properties["ndkVersion"]?.toString()
        ?: error("Field \"ndkVersion\" not found in gradle.properties")

// Java properties
public val Project.javaCompileTargetVersionValue: String
    get() = properties["compileTargetVersion"]?.toString()
        ?: error("Field \"compileTargetVersion\" not found in gradle.properties")

// Kotlin properties
public val Project.kotlinJvmToolchainVersionValue: String
    get() = properties["jvmToolchainVersion"]?.toString()
        ?: error("Field \"jvmToolchainVersion\" not found in gradle.properties")

public val Project.kotlinLanguageVersionValue: String
    get() = libs.versions.version.common.kotlin.language.orNull
        ?: error("Field \"version-common-kotlin\" not found in libs.versions.toml")

// Detekt properties
public val Project.detektVersionValue: String
    get() = libs.versions.version.utility.detekt.orNull
        ?: error("Field \"version-utility-detekt\" not found in libs.versions.toml")


// Versioning properties
public val Project.majorValue: Int
    get() = properties["MAJOR"]?.toString()?.toIntOrNull()
        ?: error("Field \"MAJOR\" not found in gradle.properties")

public val Project.minorValue: Int
    get() = properties["MINOR"]?.toString()?.toIntOrNull()
        ?: error("Field \"MINOR\" not found in gradle.properties")

public val Project.patchValue: Int
    get() = properties["PATCH"]?.toString()?.toIntOrNull()
        ?: error("Field \"PATCH\" not found in gradle.properties")
