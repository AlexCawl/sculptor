package org.alexcawl.sculptor.runtime.engine.domain.useCases

import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand.DemarshallContentCommand
import kotlin.reflect.KClass

internal class DemarshallContentUseCase(

) : SculptorUseCase<DemarshallContentCommand>() {
    override val type: KClass<DemarshallContentCommand> = DemarshallContentCommand::class

    override suspend fun TaskBuilder.handle(command: DemarshallContentCommand) {

    }
}
