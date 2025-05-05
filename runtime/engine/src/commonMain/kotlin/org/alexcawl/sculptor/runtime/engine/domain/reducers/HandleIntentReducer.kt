package org.alexcawl.sculptor.runtime.engine.domain.reducers

import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand.TransformToRequestCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent.HandleIntentEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import kotlin.reflect.KClass

internal class HandleIntentReducer : SculptorReducer<HandleIntentEvent>() {
    override val key: KClass<HandleIntentEvent> = HandleIntentEvent::class

    override suspend fun NextBuilder.reduce(event: HandleIntentEvent) {
        state {
            SculptorState.Loading
        }
        commands(
            TransformToRequestCommand(intent = event.intent),
        )
    }
}
