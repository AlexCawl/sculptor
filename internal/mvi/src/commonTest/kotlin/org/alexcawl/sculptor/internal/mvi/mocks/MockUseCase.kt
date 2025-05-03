package org.alexcawl.sculptor.internal.mvi.mocks

import org.alexcawl.sculptor.internal.mvi.core.UseCase
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Command
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Event
import kotlin.reflect.KClass

class MockUseCase : UseCase<Command, Event> {
    override val type: KClass<Command> = Command::class

    override suspend fun handle(command: Command): List<Event> = buildList {
        println("Init command: $command")
        add(element = Event.ChangeState(command.name))
        println("Finish command: $command")
    }
}
