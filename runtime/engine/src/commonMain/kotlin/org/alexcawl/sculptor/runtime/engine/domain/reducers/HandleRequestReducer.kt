package org.alexcawl.sculptor.runtime.engine.domain.reducers

import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent.HandleRequestEvent
import kotlin.reflect.KClass

internal class HandleRequestReducer : SculptorReducer<HandleRequestEvent>() {
    override val key: KClass<HandleRequestEvent> = HandleRequestEvent::class

    override suspend fun NextBuilder.reduce(event: HandleRequestEvent) {
        val (key: String, url: String) = event.request
        commands(
            SculptorCommand.LoadContentCommand(
                key = key,
                url = url,
            ),
        )
    }
}
