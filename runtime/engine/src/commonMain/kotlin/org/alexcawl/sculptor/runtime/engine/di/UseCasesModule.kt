package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.internal.di.Module
import org.alexcawl.sculptor.internal.di.factory
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.di.getOrNull
import org.alexcawl.sculptor.internal.di.module
import org.alexcawl.sculptor.internal.mvi.core.UseCase
import org.alexcawl.sculptor.runtime.engine.domain.useCases.DemarshallContentUseCase
import org.alexcawl.sculptor.runtime.engine.domain.useCases.LoadContentUseCase
import org.alexcawl.sculptor.runtime.engine.domain.useCases.SaveToCacheUseCase
import org.alexcawl.sculptor.runtime.engine.domain.useCases.TransformToLayoutUseCase
import org.alexcawl.sculptor.runtime.engine.domain.useCases.TransformToRequestUseCase

internal fun useCasesModule(): Module = module {
    factory(UseCase::class) {
        DemarshallContentUseCase(
            stringFormat = get(),
            templateAssembler = get(),
        )
    }
    factory(UseCase::class) {
        LoadContentUseCase(
            localContentSource = get(),
            remoteContentSource = get(),
            contentResolutionStrategy = get(),
        )
    }
    factory(UseCase::class) {
        SaveToCacheUseCase(
            stringFormat = get(),
            localContentSource = get(),
        )
    }
    factory(UseCase::class) {
        TransformToLayoutUseCase(
            presenterProvider = get(),
            stateValidator = get()
        )
    }
    factory(UseCase::class) {
        TransformToRequestUseCase(intentResolver = getOrNull())
    }
}