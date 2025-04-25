package org.alexcawl.sculptor.engine.di

import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.alexcawl.sculptor.common.contract.Contractor
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.di.Module
import org.alexcawl.sculptor.common.di.factory
import org.alexcawl.sculptor.common.di.get
import org.alexcawl.sculptor.common.di.getAll
import org.alexcawl.sculptor.common.di.module
import org.alexcawl.sculptor.common.presenter.PresenterProvider
import org.alexcawl.sculptor.common.presenter.PresenterScopeFactory
import org.alexcawl.sculptor.common.presenter.StateValidator
import org.alexcawl.sculptor.common.presenter.impl.PresenterProviderImpl
import org.alexcawl.sculptor.common.renderer.RendererProvider
import org.alexcawl.sculptor.common.renderer.RendererScope
import org.alexcawl.sculptor.common.renderer.impl.RendererProviderImpl
import org.alexcawl.sculptor.common.renderer.impl.RendererScopeImpl
import org.alexcawl.sculptor.engine.template.TemplateAssembler
import org.alexcawl.sculptor.engine.template.TemplateAssemblerImpl

public fun contractorModule(): Module = module {
    factory(TemplateAssembler::class) {
        TemplateAssemblerImpl(stringFormat = get())
    }
    factory(StringFormat::class) {
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            serializersModule = get()
        }
    }
    factory(SerializersModule::class) {
        val contractors: List<Contractor> = getAll()
        SerializersModule {
            polymorphic(StateContract::class) {
                contractors.forEach { contractor: Contractor ->
                    contractor.stateContracts(this)
                }
            }
            polymorphic(ModifierContract::class) {
                contractors.forEach { contractor: Contractor ->
                    contractor.modifierContracts(this)
                }
            }
        }
    }
}

public fun presenterModule(): Module = module {
    factory(StateValidator::class) {
        StateValidatorImpl(rendererProvider = get())
    }
    factory(PresenterProvider::class) {
        PresenterProviderImpl(presenters = getAll())
    }
    factory(PresenterScopeFactory::class) {
        PresenterScopeFactoryImpl(
            presenterProvider = get(),
            stateValidator = get(),
        )
    }
}

public fun rendererModule(): Module = module {
    factory(RendererProvider::class) {
        RendererProviderImpl(renderers = getAll())
    }
    factory(RendererScope::class) {
        RendererScopeImpl(rendererProvider = get())
    }
}
