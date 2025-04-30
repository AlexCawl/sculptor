package org.alexcawl.sculptor.internal.mvi.mocks.entity

data class State(val name: String) {
    companion object {
        val initial = State("initial")
        val changed = State("changed")
    }

    override fun toString() = name
}
