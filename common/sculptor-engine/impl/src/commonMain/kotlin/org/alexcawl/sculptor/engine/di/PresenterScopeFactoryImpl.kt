package org.alexcawl.sculptor.engine.di

import org.alexcawl.sculptor.common.presenter.PresenterProvider
import org.alexcawl.sculptor.common.presenter.PresenterScopeFactory
import org.alexcawl.sculptor.common.presenter.StateValidator

internal class PresenterScopeFactoryImpl(
    presenterProvider: PresenterProvider,
    stateValidator: StateValidator,
) : PresenterScopeFactory(presenterProvider, stateValidator)
