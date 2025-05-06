package org.alexcawl.sculptor.internal.di

internal expect fun <R> synchronized(lock: Any, block: () -> R): R
