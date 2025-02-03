package org.alexcawl.sculptor.common.core

import kotlin.annotation.AnnotationRetention.BINARY

/**
 * This is internal API for Sculptor, you should not rely on it.
 */
@Retention(BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY,
    AnnotationTarget.CONSTRUCTOR
)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is internal API for SDUI, you should not rely on it."
)
annotation class InternalSculptorApi
