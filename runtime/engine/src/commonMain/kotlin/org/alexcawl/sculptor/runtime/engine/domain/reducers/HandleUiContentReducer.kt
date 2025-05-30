package org.alexcawl.sculptor.runtime.engine.domain.reducers

import org.alexcawl.sculptor.core.contractor.layout.Layout
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent.HandleUiContentEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import kotlin.reflect.KClass

internal class HandleUiContentReducer : SculptorReducer<HandleUiContentEvent>() {
    override val key: KClass<HandleUiContentEvent> = HandleUiContentEvent::class

    override suspend fun NextBuilder.reduce(event: HandleUiContentEvent) {
        val layout: Layout = event.uiContent
        state {
            SculptorState.Idle(layout = layout)
        }
    }
}
