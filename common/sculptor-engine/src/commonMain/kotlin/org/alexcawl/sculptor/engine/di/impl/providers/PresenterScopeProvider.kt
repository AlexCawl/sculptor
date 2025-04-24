package org.alexcawl.sculptor.engine.di.impl.providers

import org.alexcawl.sculptor.common.presenter.ComponentProvider
import org.alexcawl.sculptor.common.presenter.OnStateCreatedCallback
import org.alexcawl.sculptor.common.presenter.PresenterProvider
import org.alexcawl.sculptor.common.presenter.PresenterScope

internal data class PresenterScopeProvider(
    private val presenterProvider: PresenterProvider,
    private val onStateCreatedCallback: OnStateCreatedCallback,
) {
    internal fun withProvided(componentProvider: ComponentProvider): PresenterScope = PresenterScope(
        presenterProvider = presenterProvider,
        componentProvider = componentProvider,
        onStateCreatedCallback = onStateCreatedCallback,
    )
}
