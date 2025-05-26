package org.alexcawl.sculptor.runtime.engine.di

import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.alexcawl.sculptor.core.contract.Contractor
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.internal.di.Module
import org.alexcawl.sculptor.internal.di.factory
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.di.getAll
import org.alexcawl.sculptor.internal.di.module
import org.alexcawl.sculptor.internal.di.singleton
import org.alexcawl.sculptor.runtime.engine.dependencies.TemplateAssembler
import org.alexcawl.sculptor.runtime.engine.dependencies.impl.TemplateAssemblerImpl

internal fun contractorModule(): Module = module {
    // Json
    singleton<StringFormat> {
        Json {
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