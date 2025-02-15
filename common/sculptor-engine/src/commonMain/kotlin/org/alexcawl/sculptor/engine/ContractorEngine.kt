package org.alexcawl.sculptor.engine

import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.alexcawl.sculptor.common.contract.ContractorState
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract

sealed interface ContractorEngine {
    fun contract(string: String) : Scaffold

    companion object Factory {
        fun create(state: ContractorState): ContractorEngine = JsonContractorEngineImpl(
            serializers = SerializersModule {
                polymorphic(ModifierContract::class, builderAction = state.modifiers)
                polymorphic(LayoutContract::class, builderAction = state.layouts)
                polymorphic(StateContract::class, builderAction = state.states)
                with(state) {
                    this@SerializersModule.contractBuilder()
                }
            },
        )
    }
}

private class JsonContractorEngineImpl(private val serializers: SerializersModule) :
    ContractorEngine {
    private val format: StringFormat by lazy {
        Json {
            prettyPrint = true
            serializersModule = serializers
            ignoreUnknownKeys = true
        }
    }

    override fun contract(string: String): Scaffold = format.decodeFromString(string)
}
