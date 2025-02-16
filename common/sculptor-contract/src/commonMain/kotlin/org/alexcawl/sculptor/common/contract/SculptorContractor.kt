package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.plus
import kotlinx.serialization.modules.polymorphic

sealed interface SculptorContractor {
    fun contract(string: String) : Scaffold

    operator fun plus(other: SculptorContractor): SculptorContractor

    sealed interface State {
        interface Json : State {
            val modifiers: PolymorphicModuleBuilder<ModifierContract>.() -> Unit
            val layouts: PolymorphicModuleBuilder<LayoutContract>.() -> Unit
            val states: PolymorphicModuleBuilder<StateContract>.() -> Unit
            val contractBuilder: SerializersModuleBuilder.() -> Unit
        }
    }

    companion object Factory {
        fun create(state: State): SculptorContractor = when (state) {
            is State.Json -> JsonImpl(
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
}

private class JsonImpl(val serializers: SerializersModule) : SculptorContractor {
    private val format: StringFormat by lazy {
        Json {
            prettyPrint = true
            serializersModule = serializers
            ignoreUnknownKeys = true
        }
    }

    override fun contract(string: String): Scaffold = format.decodeFromString(string)

    override fun plus(other: SculptorContractor): SculptorContractor = when (other) {
        is JsonImpl -> JsonImpl(
            serializers = serializers + other.serializers
        )
    }
}
