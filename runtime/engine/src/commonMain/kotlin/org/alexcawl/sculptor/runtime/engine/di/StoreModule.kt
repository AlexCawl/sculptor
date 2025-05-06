package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.internal.di.Module
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.di.getAll
import org.alexcawl.sculptor.internal.di.module
import org.alexcawl.sculptor.internal.di.singleton
import org.alexcawl.sculptor.internal.mvi.logging.StoreLogger
import org.alexcawl.sculptor.runtime.engine.dependencies.SculptorLogger
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import org.alexcawl.sculptor.runtime.engine.domain.SculptorStore

internal fun storeModule(): Module = module {
    // Store
    singleton<SculptorStore> {
        SculptorStore(
            initialState = SculptorState.Loading,
            initialCommands = emptyList(),
            useCases = getAll(),
            reducers = getAll(),
            logger = get(),
        )
    }
    singleton<StoreLogger> {
        val sculptorLogger: SculptorLogger = get()
        StoreLogger(function = sculptorLogger::debug)
    }
}