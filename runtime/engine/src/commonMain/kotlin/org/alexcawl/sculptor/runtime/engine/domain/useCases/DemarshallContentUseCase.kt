package org.alexcawl.sculptor.runtime.engine.domain.useCases

import kotlinx.serialization.StringFormat
import org.alexcawl.sculptor.core.contract.SculptorScreen
import org.alexcawl.sculptor.core.contract.SculptorScreenScaffold
import org.alexcawl.sculptor.core.contract.SculptorScreenSchema
import org.alexcawl.sculptor.runtime.engine.dependencies.template.TemplateAssembler
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
                deserializer = SculptorScreen.serializer(),
                string = rawContent,
            )
        }.onSuccess { sculptorScreen: SculptorScreen ->
            when (sculptorScreen) {
                is SculptorScreenScaffold -> {
                    dispatch {
                        SculptorEvent.HandleScaffoldEvent(key = key, scaffold = sculptorScreen)
                    }
                }

                is SculptorScreenSchema -> {
                    val scaffold: SculptorScreenScaffold = templateAssembler.assemble(
                        schema = sculptorScreen,
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
