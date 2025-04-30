package org.alexcawl.sculptor.internal.mvi.core

public abstract class UpdateDsl<State : Any, Event : Any, Command : Any, News : Any> : Update<State, Event, Command, News> {
    public final override suspend fun reduce(
        state: State,
        event: Event,
    ): Next<State, Command, News> {
        return NextBuilder(initialState = state)
            .apply { update(event = event) }
            .build()
    }

    protected abstract suspend fun NextBuilder.update(event: Event)

    protected inner class NextBuilder(initialState: State) :
        NextDsl<State, Command, News> by NextDsl.create(initialState = initialState)
}
