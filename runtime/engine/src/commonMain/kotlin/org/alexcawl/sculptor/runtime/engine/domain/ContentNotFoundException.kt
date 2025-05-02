package org.alexcawl.sculptor.runtime.engine.domain

public data class ContentNotFoundException(
    override val message: String,
    override val cause: Throwable,
) : RuntimeException()
