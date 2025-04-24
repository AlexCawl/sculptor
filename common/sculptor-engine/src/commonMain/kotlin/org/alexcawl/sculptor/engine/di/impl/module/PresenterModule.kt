package org.alexcawl.sculptor.engine.di.impl.module

import org.alexcawl.sculptor.common.di.Module
import org.alexcawl.sculptor.common.di.factory
import org.alexcawl.sculptor.common.di.get
import org.alexcawl.sculptor.common.di.getAll
import org.alexcawl.sculptor.common.di.module
import org.alexcawl.sculptor.common.presenter.OnStateCreatedCallback
import org.alexcawl.sculptor.common.presenter.PresenterProvider
import org.alexcawl.sculptor.engine.di.impl.providers.PresenterScopeProvider
import org.alexcawl.sculptor.engine.impl.OnStateCreatedCallbackImpl

internal fun presenterModule(): Module = module {
    factory {
        PresenterProvider(presenters = getAll())
    }
    factory<OnStateCreatedCallback, OnStateCreatedCallback> {
        OnStateCreatedCallbackImpl(resolveRenderer = get())
    }
    factory {
        PresenterScopeProvider(
            presenterProvider = get(),
            onStateCreatedCallback = get(),
        )
    }
}
