package org.alexcawl.sculptor.internal.mvi.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import org.alexcawl.sculptor.internal.mvi.core.impl.StoreImpl

/**
 * Represents a Model-View-Intent (MVI) store that manages the state and handles events.
 *
 * @param State The type of the state managed by the store.
 * @param Event The type of events that the store can dispatch.
 */
public interface Store<out State : Any, in Event : Any> {

    /**
     * A [StateFlow] that emits the current state of the store.
     */
    public val state: StateFlow<State>

    /**
     * Dispatches an event to the store, which may trigger state changes or side effects.
     *
     * @param event The event to be dispatched.
     */
    public fun dispatch(event: Event)

    /**
     * Launches the store within the provided [CoroutineScope].
     *
     * @param coroutineScope The scope in which the store should operate.
     */
    public fun launchIn(coroutineScope: CoroutineScope)

    /**
     * Companion object providing a factory method to create a [Store] instance.
     */
    public companion object {

        /**
         * Creates a new instance of [Store].
         *
         * @param initialState The initial state of the store.
         * @param initialCommands A list of initial commands to be processed.
         * @param useCases A list of [UseCase] instances that handle commands and produce events.
         * @param reducers A list of [Reducer] instances that update the state based on events and commands.
         * @return A new [Store] instance.
         */
        public fun <State : Any, Event : Any, Command : Any> create(
            initialState: State,
            initialCommands: List<Command> = emptyList(),
            useCases: List<UseCase<Command, Event>> = emptyList(),
            reducers: List<Reducer<State, Event, Command>> = emptyList(),
        ): Store<State, Event> {
            return StoreImpl(
                initialState = initialState,
                initialCommands = initialCommands,
                useCases = useCases,
                reducers = reducers,
            )
        }
    }
}
