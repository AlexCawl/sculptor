package org.alexcawl.sculptor.runtime.engine.useCases

import org.alexcawl.sculptor.runtime.engine.mvi.SculptorCommand.LoadContentCommand
import org.alexcawl.sculptor.runtime.engine.mvi.SculptorUseCase
import kotlin.reflect.KClass

internal class LoadContentByUrlUseCase : SculptorUseCase<LoadContentCommand>() {
    override val key: KClass<LoadContentCommand> = LoadContentCommand::class

    override suspend fun TaskBuilder.handle(command: LoadContentCommand) {

    }
}
