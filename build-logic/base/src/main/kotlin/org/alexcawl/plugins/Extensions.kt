package org.alexcawl.plugins

import org.gradle.api.Project

val Project.projectVersionCodeValue: Int
    get() = majorValue * 100 + minorValue * 10 + patchValue

val Project.projectVersionNameValue: String
    get() = "${majorValue}.${minorValue}.${patchValue}"
