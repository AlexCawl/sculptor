package org.alexcawl.sculptor.engine.di.impl.module

import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.alexcawl.sculptor.common.contract.Assembler
import org.alexcawl.sculptor.common.contract.Contractor
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.di.Module
import org.alexcawl.sculptor.common.di.get
import org.alexcawl.sculptor.common.di.getAll
import org.alexcawl.sculptor.common.di.module
import org.alexcawl.sculptor.common.di.singleton

internal fun contractorModule(): Module = module {
    singleton<StringFormat, StringFormat> {
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            prettyPrint = true
            serializersModule = get()
        }
    }
    singleton {
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
    singleton {
        Assembler(stringFormat = get())
    }
}
