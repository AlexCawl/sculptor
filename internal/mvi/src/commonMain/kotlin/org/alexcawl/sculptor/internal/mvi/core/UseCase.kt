package org.alexcawl.sculptor.internal.mvi.core

import kotlin.reflect.KClass

public interface UseCase<Command : Any, Event : Any> {
    public val key: KClass<Command>

    public fun handle(command: Command): List<Event>
}
