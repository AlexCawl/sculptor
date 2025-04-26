package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.presenter.impl.PresenterScopeImpl

/**
 * Factory class responsible for creating instances of [PresenterScope].
 *
 * @property presenterProvider Provides instances of presenters.
 * @property stateValidator Validates the state of the presenter.
 */
public abstract class PresenterScopeFactory(
    private val presenterProvider: PresenterProvider,
    private val stateValidator: StateValidator,
) {
    /**
     * Creates a new instance of [PresenterScope] using the provided [ComponentProvider].
     *
     * @param componentProvider Provides necessary components for the presenter scope.
     * @return A new instance of [PresenterScope].
     */
    public fun create(componentProvider: ComponentProvider): PresenterScope {
        return PresenterScopeImpl(
            presenterProvider = presenterProvider,
            componentProvider = componentProvider,
            stateValidator = stateValidator,
        )
    }
}
