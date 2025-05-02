package org.alexcawl.sculptor.internal.mvi.logging

public fun interface UpdateLogger {
    public fun log(tag: String, message: String)

    public companion object NoOp : UpdateLogger {
        override fun log(tag: String, message: String): Unit = Unit
    }
}
