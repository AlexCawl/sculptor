package org.alexcawl.sculptor.internal.mvi.core

import kotlin.reflect.KClass

/**
 * Represents a reducer in the Model-View-Intent (MVI) architecture.
 * A reducer is responsible for handling events and producing the next state and commands.
 *
 * @param State The type of the state that the reducer operates on.
 * @param Event The type of events that the reducer can handle.
 * @param Command The type of commands that the reducer can produce.
 */
public interface Reducer<State : Any, Event : Any, out Command : Any> {

    /**
     * The key associated with the type of events this reducer handles.
     * This is typically the class of the event.
     */
    public val key: KClass<Event>

    /**
     * Reduces the current state and an event to produce the next state and commands.
     *
     * @param state The current state of the system.
     * @param event The event to be handled.
     * @return A [Next] object containing the next state and any commands to be executed.
     */
    public suspend fun reduce(state: State, event: Event): Next<State, Command>
}
