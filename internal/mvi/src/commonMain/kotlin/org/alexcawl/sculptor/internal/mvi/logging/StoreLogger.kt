package org.alexcawl.sculptor.internal.mvi.logging

public fun interface StoreLogger {
    public fun log(tag: String, message: String)

    public companion object NoOp : StoreLogger {
        override fun log(tag: String, message: String): Unit = Unit
    }
}
