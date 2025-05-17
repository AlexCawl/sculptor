package org.alexcawl.plugins.base

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

public val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

public val Project.projectVersionCodeValue: Int
    get() = majorValue * 100 + minorValue * 10 + patchValue

public val Project.projectVersionNameValue: String
    get() = "${majorValue}.${minorValue}.${patchValue}"
