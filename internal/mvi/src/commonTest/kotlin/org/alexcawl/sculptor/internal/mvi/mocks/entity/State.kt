package org.alexcawl.sculptor.internal.mvi.mocks.entity

sealed interface State {
    val name: String

    data object Initial : State {
        override val name: String = "initial"
    }

    data class Content(override val name: String) : State
}
