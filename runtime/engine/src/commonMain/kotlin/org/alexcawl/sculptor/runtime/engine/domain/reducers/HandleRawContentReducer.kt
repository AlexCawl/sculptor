package org.alexcawl.sculptor.runtime.engine.domain.reducers

import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent.HandleRawContentEvent
import kotlin.reflect.KClass

internal class HandleRawContentReducer : SculptorReducer<HandleRawContentEvent>() {
    override val key: KClass<HandleRawContentEvent> = HandleRawContentEvent::class

    override suspend fun NextBuilder.reduce(event: HandleRawContentEvent) {
        val (key: String, rawContent: String) = event
        commands(
            SculptorCommand.DemarshallContentCommand(key = key, rawContent = rawContent)
        )
    }
}
