package org.alexcawl.sculptor.internal.mvi.core

public fun interface Update<State : Any, in Event : Any, out Command : Any, out News : Any> {
    public suspend fun reduce(state: State, event: Event): Next<State, Command, News>
}
