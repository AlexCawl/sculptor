package org.alexcawl.sculptor.runtime.engine.domain.useCases

import org.alexcawl.sculptor.runtime.engine.dependencies.intent.IntentResolver
import org.alexcawl.sculptor.runtime.engine.domain.IntentNotResolvedException
import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand.TransformToRequestCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorRequest
import org.alexcawl.sculptor.runtime.engine.ui.SculptorDeeplinkIntent
import kotlin.reflect.KClass

internal class TransformToRequestUseCase(
    private val intentResolver: IntentResolver,
) : SculptorUseCase<TransformToRequestCommand>() {
    override val type: KClass<TransformToRequestCommand> = TransformToRequestCommand::class

    override suspend fun TaskBuilder.handle(command: TransformToRequestCommand) {
        val sculptorRequest: SculptorRequest? = when (val intent = command.intent) {
            is SculptorDeeplinkIntent -> {
                SculptorRequest(
                    key = intent.payload,
                    url = intent.payload,
                )
            }
            else -> intentResolver.resolve(intent)
        }
        when (sculptorRequest) {
            null -> {
                dispatch {
                    SculptorEvent.HandleFailureEvent(cause = IntentNotResolvedException)
                }
            }
            else -> {
                dispatch {
                    SculptorEvent.HandleRequestEvent(request = sculptorRequest)
                }
            }
        }
    }
}
