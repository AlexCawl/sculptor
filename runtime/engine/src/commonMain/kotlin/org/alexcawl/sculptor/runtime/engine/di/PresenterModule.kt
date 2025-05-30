package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.internal.di.Module
import org.alexcawl.sculptor.internal.di.factory
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.di.getAll
import org.alexcawl.sculptor.internal.di.module
import org.alexcawl.sculptor.runtime.engine.domain.StateValidatorImpl
import org.alexcawl.sculptor.runtime.contractor.presenter.PresenterProvider
import org.alexcawl.sculptor.runtime.contractor.presenter.StateValidator
import org.alexcawl.sculptor.runtime.contractor.presenter.impl.PresenterProviderImpl

internal fun presenterModule(): Module = module {
    factory<PresenterProvider> {
        PresenterProviderImpl(presenters = getAll())
    }
    factory<StateValidator> {
        StateValidatorImpl(rendererProvider = get())
    }
}