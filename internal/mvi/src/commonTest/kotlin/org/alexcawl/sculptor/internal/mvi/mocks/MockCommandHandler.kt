package org.alexcawl.sculptor.internal.mvi.mocks

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import org.alexcawl.sculptor.internal.mvi.core.CommandHandler
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Command
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Event

class MockCommandHandler : CommandHandler<Command, Event> {
    override fun handle(commands: Flow<Command>): Flow<Event> {
        return commands
            .filterIsInstance<Command>()
            .map { command: Command ->
                Event(name = command.name)
            }
    }
}
