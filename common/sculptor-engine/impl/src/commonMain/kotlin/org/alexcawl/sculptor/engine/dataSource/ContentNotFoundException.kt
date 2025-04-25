package org.alexcawl.sculptor.engine.dataSource

public data class ContentNotFoundException(
    override val message: String,
    override val cause: Throwable,
) : RuntimeException()
