package org.alexcawl.sculptor.internal.mvi.core

/**
 * Represents the next state and commands to be processed in an MVI (Model-View-Intent) architecture.
 *
 * @param State The type of the state being managed, which must be a subtype of [Any].
 * @param Command The type of the commands to be executed, which must be a subtype of [Any].
 * @property state The new state to be set, or `null` if the state should remain unchanged.
 * @property commands A list of commands to be executed as a result of the current state transition.
 */
public data class Next<out State : Any, out Command : Any>(
    public val state: State? = null,
    public val commands: List<Command> = emptyList(),
)
