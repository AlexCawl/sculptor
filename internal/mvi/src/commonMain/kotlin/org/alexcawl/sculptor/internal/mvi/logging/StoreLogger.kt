package org.alexcawl.sculptor.internal.mvi.logging

/**
 * Interface for logging messages within the MVI (Model-View-Intent) architecture.
 * Implementations of this interface are responsible for handling log messages with a given tag.
 */
public fun interface StoreLogger {
    /**
     * Logs a message with the specified tag.
     *
     * @param tag The tag associated with the log message.
     * @param message The log message to be logged.
     */
    public fun log(tag: String, message: String)

    /**
     * A no-operation (NoOp) implementation of [StoreLogger].
     * This implementation does nothing when the [log] method is called.
     */
    public companion object NoOp : StoreLogger {
        override fun log(tag: String, message: String): Unit = Unit
    }
}
