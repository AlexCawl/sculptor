package org.alexcawl.sculptor.internal.mvi.core

import org.alexcawl.sculptor.internal.mvi.core.impl.NextDslImpl

/**
 * Interface for building the next state and commands in a Model-View-Intent (MVI) architecture.
 *
 * @param State The type of the state in the MVI architecture.
 * @param Command The type of the commands in the MVI architecture.
 */
public interface NextDsl<State : Any, Command : Any> {

    /**
     * Updates the state using a block that operates on the current state.
     *
     * @param block A lambda function that takes the current state and returns the new state.
     */
    public fun state(block: State.() -> State)

    /**
     * Adds commands to be executed.
     *
     * @param commands A vararg of commands to be added. Null commands are ignored.
     */
    public fun commands(vararg commands: Command?)

    /**
     * Builds and returns the [Next] object containing the updated state and commands.
     *
     * @return A [Next] object with the updated state and commands.
     */
    public fun build(): Next<State, Command>

    /**
     * Companion object to create an instance of [NextDsl].
     */
    public companion object {
        /**
         * Creates an instance of [NextDsl] with the given initial state.
         *
         * @param initialState The initial state for the MVI architecture.
         * @return An instance of [NextDsl] initialized with the provided state.
         */
        public fun <State : Any, Command : Any> create(initialState: State): NextDsl<State, Command> {
            return NextDslImpl(initialState = initialState)
        }
    }
}
