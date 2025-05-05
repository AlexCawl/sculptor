package org.alexcawl.sculptor.internal.di

internal actual fun <R> synchronized(lock: Any, block: () -> R): R {
    // in wasmJs we do not need sync
    return block()
}
