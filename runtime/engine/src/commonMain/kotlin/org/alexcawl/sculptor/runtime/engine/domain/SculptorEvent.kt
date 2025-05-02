package org.alexcawl.sculptor.runtime.engine.domain

import org.alexcawl.sculptor.core.contract.SculptorContent
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.runtime.engine.ui.SculptorIntent

internal interface SculptorEvent {
    data class HandleIntentEvent(val intent: SculptorIntent) : SculptorEvent

    data class HandleRequestEvent(val request: SculptorRequest) : SculptorEvent

    data class HandleRawContentEvent(val rawContent: String) : SculptorEvent

    data class HandleContentEvent(val content: SculptorContent) : SculptorEvent

    data class HandleUiContentEvent(val uiContent: Layout) : SculptorEvent

    data class FailureEvent(val cause: Throwable) : SculptorEvent
}
