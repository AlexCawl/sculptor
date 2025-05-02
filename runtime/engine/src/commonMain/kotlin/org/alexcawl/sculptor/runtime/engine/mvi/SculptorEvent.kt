package org.alexcawl.sculptor.runtime.engine.mvi

internal interface SculptorEvent {
    data class LoadInitialContentEvent(val deeplink: String) : SculptorEvent

    data class LoadContentEvent(val key: String, val url: String) : SculptorEvent
}
