package org.alexcawl.sculptor.runtime.engine.domain

import org.alexcawl.sculptor.core.contract.ScreenScaffold
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.runtime.engine.SculptorIntent

internal interface SculptorEvent {
    data class HandleIntentEvent(val intent: SculptorIntent) : SculptorEvent

    data class HandleRequestEvent(val request: SculptorRequest) : SculptorEvent

    data class HandleRawContentEvent(val key: String, val rawContent: String) : SculptorEvent

    data class HandleScaffoldEvent(val key: String, val scaffold: ScreenScaffold) : SculptorEvent

    data class HandleUiContentEvent(val uiContent: Layout) : SculptorEvent

    data class HandleFailureEvent(val cause: Throwable, val type: Type = Type.CRITICAL) : SculptorEvent {
        enum class Type {
            UNIMPORTANT,
            CRITICAL,
        }
    }
}
