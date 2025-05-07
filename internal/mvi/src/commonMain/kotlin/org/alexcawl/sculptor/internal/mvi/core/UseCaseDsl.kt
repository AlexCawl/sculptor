package org.alexcawl.sculptor.internal.mvi.core

/**
 * Abstract class representing a Use Case DSL that handles commands and produces events.
 *
 * @param Command The type of command that the use case will handle.
 * @param Event The type of event that the use case will produce.
 */
public abstract class UseCaseDsl<Command : Any, Event : Any> : UseCase<Command, Event> {

    /**
     * Final implementation of the handle function that processes a command and returns a list of events.
     * It uses a [TaskBuilder] to build the list of events.
     *
     * @param command The command to be handled.
     * @return A list of events produced as a result of handling the command.
     */
    final override suspend fun handle(command: Command): List<Event> {
        return TaskBuilder()
            .apply { handle(command = command) }
            .build()
    }

    /**
     * Abstract function to be implemented by subclasses to define how a command is handled.
     * This function should use the [TaskBuilder] to produce events.
     *
     * @param command The command to be handled.
     */
    protected abstract suspend fun TaskBuilder.handle(command: Command)

    /**
     * Inner class that extends [TaskDsl] to provide a DSL for building tasks.
     * It delegates its functionality to an instance of [TaskDsl].
     */
    protected inner class TaskBuilder : TaskDsl<Event> by TaskDsl.create()
}
