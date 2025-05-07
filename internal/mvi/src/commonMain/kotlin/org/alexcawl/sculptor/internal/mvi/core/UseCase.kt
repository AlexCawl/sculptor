package org.alexcawl.sculptor.internal.mvi.core

import kotlin.reflect.KClass

/**
 * Represents a use case in the MVI (Model-View-Intent) architecture.
 *
 * A use case is responsible for handling commands and producing events based on those commands.
 *
 * @param Command The type of command that this use case can handle.
 * @param Event The type of event that this use case can produce.
 */
public interface UseCase<Command : Any, Event : Any> {

    /**
     * The type of command that this use case can handle.
     */
    public val type: KClass<Command>

    /**
     * Handles a command and returns a list of events.
     *
     * @param command The command to be handled.
     * @return A list of events produced as a result of handling the command.
     */
    public suspend fun handle(command: Command): List<Event>
}
