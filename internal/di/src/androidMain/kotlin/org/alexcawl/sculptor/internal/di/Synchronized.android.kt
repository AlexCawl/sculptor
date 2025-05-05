package org.alexcawl.sculptor.internal.di

internal actual inline fun <R> synchronized(lock: Any, block: () -> R): R {
    return kotlin.synchronized(lock, block)
}
