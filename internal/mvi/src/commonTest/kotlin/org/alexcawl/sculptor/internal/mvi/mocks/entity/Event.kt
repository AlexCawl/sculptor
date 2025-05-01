package org.alexcawl.sculptor.internal.mvi.mocks.entity

sealed interface Event {
    data class ChangeState(val name: String) : Event

    data class SendCommand(val name: String) : Event
}
