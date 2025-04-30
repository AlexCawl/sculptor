package org.alexcawl.sculptor.internal.mvi.mocks.entity

open class Event(val name: String) {
    companion object {
        val smthLoaded = UiEvent("smthLoaded")
    }

    override fun toString() = name
}

