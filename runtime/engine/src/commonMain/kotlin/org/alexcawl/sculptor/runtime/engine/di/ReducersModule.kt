package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.internal.di.Module
import org.alexcawl.sculptor.internal.di.factory
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.di.module
import org.alexcawl.sculptor.internal.mvi.core.Reducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleFailureReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleIntentReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleRawContentReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleRequestReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleScaffoldReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleUiContentReducer

internal fun reducersModule(): Module = module {
    factory(Reducer::class) {
        HandleFailureReducer(sculptorLogger = get())
    }
    factory(Reducer::class) {
        HandleIntentReducer()
    }
    factory(Reducer::class) {
        HandleRawContentReducer()
    }
    factory(Reducer::class) {
        HandleRequestReducer()
    }
    factory(Reducer::class) {
        HandleScaffoldReducer()
    }
    factory(Reducer::class) {
        HandleUiContentReducer()
    }
}