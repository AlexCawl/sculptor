package org.alexcawl.sculptor.internal.mvi.mocks.entity

class News(val name: String) {
    companion object {
        val hello = News("hello")
    }

    override fun toString() = name
}
