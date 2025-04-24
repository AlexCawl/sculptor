package org.alexcawl.sculptor.engine.di.impl.module

import org.alexcawl.sculptor.common.di.Module
import org.alexcawl.sculptor.common.di.factory
import org.alexcawl.sculptor.common.di.get
import org.alexcawl.sculptor.common.di.getAll
import org.alexcawl.sculptor.common.di.module
import org.alexcawl.sculptor.common.presenter.ComponentProvider
import org.alexcawl.sculptor.common.presenter.PresenterProvider
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StateCreateCallback
import org.alexcawl.sculptor.engine.impl.LayoutValidationService

internal fun presenterModule(): Module = module {
    factory {
        PresenterProvider(presenters = getAll())
    }
    factory<StateCreateCallback, StateCreateCallback> {
        LayoutValidationService(resolveRenderer = get())
    }
    factory {
        PresenterScope(
            presenterProvider = get(),
            componentProvider = get(),
            stateCreateCallback = get(),
        )
    }
}
