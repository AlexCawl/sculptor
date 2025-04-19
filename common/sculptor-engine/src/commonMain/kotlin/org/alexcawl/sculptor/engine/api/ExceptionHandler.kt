package org.alexcawl.sculptor.engine.api

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

public interface ExceptionHandler : CoroutineExceptionHandler {
    override fun handleException(context: CoroutineContext, exception: Throwable)
}
