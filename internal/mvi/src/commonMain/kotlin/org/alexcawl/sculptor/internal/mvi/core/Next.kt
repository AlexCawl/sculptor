package org.alexcawl.sculptor.internal.mvi.core

public class Next<out State : Any, out Command : Any, out News : Any>(
    public val state: State? = null,
    public val commands: List<Command> = emptyList(),
    public val news: List<News> = emptyList(),
)
