package org.alexcawl.sculptor.internal.mvi.core

import kotlinx.coroutines.flow.Flow

public fun interface CommandHandler<in Command : Any, out Event : Any> {
    public fun handle(commands: Flow<Command>): Flow<Event>
}
