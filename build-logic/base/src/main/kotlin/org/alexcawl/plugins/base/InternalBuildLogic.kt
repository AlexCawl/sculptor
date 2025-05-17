package org.alexcawl.plugins.base

import kotlin.annotation.AnnotationRetention.BINARY

@Retention(BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is internal API, you should not rely on it."
)
public annotation class InternalBuildLogic
