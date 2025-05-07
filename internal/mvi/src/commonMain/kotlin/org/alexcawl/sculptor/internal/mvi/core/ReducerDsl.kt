package org.alexcawl.sculptor.internal.mvi.core

/**
 * An abstract class that serves as a DSL (Domain Specific Language) for defining reducers in the MVI (Model-View-Intent) architecture.
 * It provides a structured way to handle state transitions based on events and generate commands.
 *
 * @param State The type of the state that the reducer operates on.
 * @param Event The type of events that the reducer can handle.
 * @param Command The type of commands that the reducer can produce.
 */
public abstract class ReducerDsl<State : Any, Event : Any, Command : Any> : Reducer<State, Event, Command> {

    /**
     * Final implementation of the reduce function that uses a [NextBuilder] to construct the next state and command.
     * This function is called by the MVI framework to handle an event and transition the state.
     *
     * @param state The current state before the event is processed.
     * @param event The event that needs to be processed.
     * @return A [Next] object containing the new state and any commands to be executed.
     */
    public final override suspend fun reduce(state: State, event: Event): Next<State, Command> {
        return NextBuilder(initialState = state)
            .apply { reduce(event = event) }
            .build()
    }

    /**
     * Abstract function that must be implemented by subclasses to define how the state should be updated
     * and what commands should be generated in response to a given event.
     *
     * @param event The event that needs to be processed.
     */
    protected abstract suspend fun NextBuilder.reduce(event: Event)

    /**
     * Inner class that extends [NextDsl] to provide a DSL for building the next state and command.
     * It uses the initial state provided to the [ReducerDsl] to start the state transition process.
     *
     * @param initialState The initial state before any changes are made.
     */
    protected inner class NextBuilder(initialState: State) :
        NextDsl<State, Command> by NextDsl.create(initialState = initialState)
}
