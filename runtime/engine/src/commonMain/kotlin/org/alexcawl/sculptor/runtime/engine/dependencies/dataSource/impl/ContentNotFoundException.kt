package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.impl

public data class ContentNotFoundException(
    override val message: String,
    override val cause: Throwable,
) : RuntimeException()
