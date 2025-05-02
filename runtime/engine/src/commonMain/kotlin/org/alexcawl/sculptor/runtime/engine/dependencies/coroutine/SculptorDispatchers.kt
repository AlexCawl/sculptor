package org.alexcawl.sculptor.runtime.engine.dependencies.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

public data class SculptorDispatchers(
    public val main: CoroutineDispatcher = Dispatchers.Main,
    public val io: CoroutineDispatcher = Dispatchers.IO,
    public val computation: CoroutineDispatcher = Dispatchers.Default,
    public val immediate: CoroutineDispatcher = Dispatchers.Main.immediate,
)
