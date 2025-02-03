package org.alexcawl.sculptor.common.core

import kotlin.annotation.AnnotationRetention.BINARY

/**
 * This is an experimental API for Sculptor and is likely to change
 */
@Retention(BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is an experimental API for SDUI and is likely to change"
)
annotation class ExperimentalSculptorApi
