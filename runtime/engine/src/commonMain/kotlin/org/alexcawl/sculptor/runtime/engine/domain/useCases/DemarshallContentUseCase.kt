package org.alexcawl.sculptor.runtime.engine.domain.useCases

import kotlinx.serialization.StringFormat
import org.alexcawl.sculptor.core.contract.Screen
import org.alexcawl.sculptor.core.contract.ScreenScaffold
import org.alexcawl.sculptor.core.contract.ScreenSchema
import org.alexcawl.sculptor.runtime.engine.dependencies.TemplateAssembler
import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand.DemarshallContentCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import kotlin.reflect.KClass

internal class DemarshallContentUseCase(
    private val stringFormat: StringFormat,
    private val templateAssembler: TemplateAssembler,
) : SculptorUseCase<DemarshallContentCommand>() {
    override val type: KClass<DemarshallContentCommand> = DemarshallContentCommand::class

    override suspend fun TaskBuilder.handle(command: DemarshallContentCommand) {
        val (key: String, rawContent: String) = command
        runCatching {
            stringFormat.decodeFromString(
                deserializer = Screen.serializer(),
                string = rawContent,
            )
        }.onSuccess { screen: Screen ->
            when (screen) {
                is ScreenScaffold -> {
                    dispatch {
                        SculptorEvent.HandleScaffoldEvent(key = key, scaffold = screen)
                    }
                }

                is ScreenSchema -> {
                    val scaffold: ScreenScaffold = templateAssembler.assemble(
                        schema = screen,
                    )
                    dispatch {
                        SculptorEvent.HandleScaffoldEvent(key = key, scaffold = scaffold)
                    }
                }
            }
        }.onFailure { exception: Throwable ->
            dispatch {
                SculptorEvent.HandleFailureEvent(cause = exception)
            }
        }
    }
}
