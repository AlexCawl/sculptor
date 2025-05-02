package org.alexcawl.sculptor.runtime.engine.domain

internal data object IntentNotResolvedException : RuntimeException() {
    private fun readResolve(): Any = IntentNotResolvedException
}
