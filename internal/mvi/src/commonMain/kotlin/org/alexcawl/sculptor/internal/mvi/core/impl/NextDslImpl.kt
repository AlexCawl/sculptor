package org.alexcawl.sculptor.internal.mvi.core.impl

import org.alexcawl.sculptor.internal.mvi.core.Next
import org.alexcawl.sculptor.internal.mvi.core.NextDsl

/**
 * Implementation of [NextDsl] that manages the state and commands.
 *
 * @param State The type of the state in the MVI architecture.
 * @param Command The type of the commands in the MVI architecture.
 * @param initialState The initial state for the MVI architecture.
 */
internal class NextDslImpl<State : Any, Command : Any>(initialState: State) :
    NextDsl<State, Command> {
    private var state: State = initialState
    private val commands: MutableList<Command> = mutableListOf()

    override fun state(block: State.() -> State) {
        state = state.block()
    }

    override fun commands(vararg commands: Command?) {
        for (item in commands) {
            if (item != null) {
                this.commands.add(item)
            }
        }
    }

    override fun build(): Next<State, Command> {
        return Next(
            state = state,
            commands = commands,
        )
    }
}