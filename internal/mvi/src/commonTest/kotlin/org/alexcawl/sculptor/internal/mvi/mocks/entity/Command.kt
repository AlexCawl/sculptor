package org.alexcawl.sculptor.internal.mvi.mocks.entity

open class Command(val name: String) {
    companion object {
        val loadSmth = Command("loadSmth")
    }

    override fun toString() = name
}
