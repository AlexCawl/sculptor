package org.alexcawl.sculptor.internal.mvi.core

public abstract class UseCaseDsl<Command : Any, Event : Any> : UseCase<Command, Event> {
    final override suspend fun handle(command: Command): List<Event> {
        return TaskBuilder()
            .apply { handle(command = command) }
            .build()
    }

    protected abstract suspend fun TaskBuilder.handle(command: Command)

    protected inner class TaskBuilder : TaskDsl<Event> by TaskDsl.create()
}
