package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.presenter.impl.PresenterScopeImpl

public abstract class PresenterScopeFactory(
    private val presenterProvider: PresenterProvider,
    private val stateValidator: StateValidator,
) {
    public fun create(componentProvider: ComponentProvider): PresenterScope {
        return PresenterScopeImpl(
            presenterProvider = presenterProvider,
            componentProvider = componentProvider,
            stateValidator = stateValidator,
        )
    }
}
