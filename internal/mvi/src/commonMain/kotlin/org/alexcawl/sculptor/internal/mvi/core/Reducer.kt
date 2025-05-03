package org.alexcawl.sculptor.internal.mvi.core

import kotlin.reflect.KClass

public interface Reducer<State : Any, Event : Any, out Command : Any> {
    public val key: KClass<Event>

    public suspend fun reduce(state: State, event: Event): Next<State, Command>
}
