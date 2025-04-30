package org.alexcawl.sculptor.internal.mvi.mocks.entity

class UiEvent(val name: String) {
    companion object {
        val smthClicked = UiEvent("smthClicked")
    }

    override fun toString() = name
}
