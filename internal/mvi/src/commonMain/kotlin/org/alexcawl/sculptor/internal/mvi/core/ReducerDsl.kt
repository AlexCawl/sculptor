package org.alexcawl.sculptor.internal.mvi.core

public abstract class ReducerDsl<State : Any, Event : Any, Command : Any> : Reducer<State, Event, Command> {
    public final override suspend fun reduce(
        state: State,
        event: Event,
    ): Next<State, Command> {
        return NextBuilder(initialState = state)
            .apply { update(event = event) }
            .build()
    }

    protected abstract suspend fun NextBuilder.update(event: Event)

    protected inner class NextBuilder(initialState: State) :
        NextDsl<State, Command> by NextDsl.create(initialState = initialState)
}
