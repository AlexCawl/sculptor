package org.alexcawl.sculptor.engine.impl.contentService

internal data class ContentNotFoundException(
    override val message: String,
    override val cause: Throwable,
) : RuntimeException()
