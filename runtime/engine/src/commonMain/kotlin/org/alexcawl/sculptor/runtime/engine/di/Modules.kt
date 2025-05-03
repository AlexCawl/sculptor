package org.alexcawl.sculptor.runtime.engine.di

import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.alexcawl.sculptor.core.contract.Contractor
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.core.renderer.RendererScope
import org.alexcawl.sculptor.internal.di.Module
import org.alexcawl.sculptor.internal.di.factory
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.di.getAll
import org.alexcawl.sculptor.internal.di.getOrNull
import org.alexcawl.sculptor.internal.di.module
import org.alexcawl.sculptor.internal.di.singleton
import org.alexcawl.sculptor.internal.mvi.core.Reducer
import org.alexcawl.sculptor.internal.mvi.core.UseCase
import org.alexcawl.sculptor.internal.mvi.logging.StoreLogger
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.LocalContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.impl.InMemoryLocalContentSourceImpl
import org.alexcawl.sculptor.runtime.engine.dependencies.logger.SculptorLogger
import org.alexcawl.sculptor.runtime.engine.dependencies.logger.impl.NoOpSculptorLoggerImpl
import org.alexcawl.sculptor.runtime.engine.dependencies.template.TemplateAssembler
import org.alexcawl.sculptor.runtime.engine.dependencies.template.impl.TemplateAssemblerImpl
import org.alexcawl.sculptor.runtime.engine.domain.SculptorStore
import org.alexcawl.sculptor.runtime.engine.domain.StateValidatorImpl
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleFailureReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleIntentReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleRawContentReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleRequestReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleScaffoldReducer
import org.alexcawl.sculptor.runtime.engine.domain.reducers.HandleUiContentReducer
import org.alexcawl.sculptor.runtime.engine.domain.useCases.DemarshallContentUseCase
import org.alexcawl.sculptor.runtime.engine.domain.useCases.LoadContentUseCase
import org.alexcawl.sculptor.runtime.engine.domain.useCases.SaveToCacheUseCase
import org.alexcawl.sculptor.runtime.engine.domain.useCases.TransformToLayoutUseCase
import org.alexcawl.sculptor.runtime.engine.domain.useCases.TransformToRequestUseCase
import org.alexcawl.sculptor.runtime.engine.ui.SculptorState
import org.alexcawl.sculptor.runtime.presenter.PresenterProvider
import org.alexcawl.sculptor.runtime.presenter.StateValidator
import org.alexcawl.sculptor.runtime.presenter.impl.PresenterProviderImpl
import org.alexcawl.sculptor.runtime.renderer.RendererProvider
import org.alexcawl.sculptor.runtime.renderer.impl.RendererProviderImpl
import org.alexcawl.sculptor.runtime.renderer.impl.RendererScopeImpl

internal fun storeModule(): Module = module {
    // Store
    singleton<SculptorStore> {
        SculptorStore(
            initialState = SculptorState.Initial,
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

internal fun contractorModule(): Module = module {
    // Json
    singleton<StringFormat> {
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            prettyPrint = true
            serializersModule = get()
        }
    }

    // Serializers
    singleton<SerializersModule> {
        SerializersModule {
            getAll<Contractor>().forEach { contractor: Contractor ->
                polymorphic(StateContract::class) {
                    contractor.stateContracts.invoke(this)
                }
                polymorphic(ModifierContract::class) {
                    contractor.modifierContracts.invoke(this)
                }
            }
        }
    }

    // Template Assembler
    factory<TemplateAssembler> {
        TemplateAssemblerImpl(stringFormat = get())
    }
}

internal fun presenterModule(): Module = module {
    factory<PresenterProvider> {
        PresenterProviderImpl(presenters = getAll())
    }
    factory<StateValidator> {
        StateValidatorImpl(rendererProvider = get())
    }
}

internal fun rendererModule(): Module = module {
    factory<RendererProvider> {
        RendererProviderImpl(renderers = getAll())
    }
    factory<RendererScope> {
        RendererScopeImpl(rendererProvider = get())
    }
}

internal fun defaultDependenciesModule(): Module = module {
    singleton<SculptorLogger> {
        NoOpSculptorLoggerImpl
    }
    singleton<LocalContentSource> {
        InMemoryLocalContentSourceImpl()
    }
}
