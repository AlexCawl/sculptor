package org.alexcawl.sculptor.runtime.engine.domain.useCases

import kotlinx.serialization.StringFormat
import org.alexcawl.sculptor.core.contract.Screen
import org.alexcawl.sculptor.core.contract.ScreenScaffold
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.LocalContentSource
import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand.SaveToCacheCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import kotlin.reflect.KClass

internal class SaveToCacheUseCase(
    private val stringFormat: StringFormat,
    private val localContentSource: LocalContentSource,
) : SculptorUseCase<SaveToCacheCommand>() {
    override val type: KClass<SaveToCacheCommand> = SaveToCacheCommand::class

    override suspend fun TaskBuilder.handle(command: SaveToCacheCommand) {
        val (key: String, scaffold: ScreenScaffold) = command
        runCatching {
            stringFormat.encodeToString(
                serializer = Screen.serializer(),
                value = scaffold,
            )
        }.onSuccess { content: String ->
            localContentSource.save(key = key, content = content)
        }.onFailure { exception: Throwable ->
            dispatch {
                SculptorEvent.HandleFailureEvent(
                    cause = exception,
                    type = SculptorEvent.HandleFailureEvent.Type.UNIMPORTANT,
                )
            }
        }
    }
}
