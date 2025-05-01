package org.alexcawl.sculptor.internal.mvi.core

public data class Next<out State : Any, out Command : Any>(
    public val state: State? = null,
    public val commands: List<Command> = emptyList(),
)
