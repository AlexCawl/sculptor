package org.alexcawl.sculptor.engine

import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.plus
import kotlinx.serialization.modules.polymorphic
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.StateContract

/**
 * TODO: docs
 */
public sealed interface SculptorContractor {
    /**
     * TODO: docs
     */
    public fun decode(string: String) : Scaffold

    /**
     * TODO: docs
     */
    public fun encode(scaffold: Scaffold): String

    /**
     * TODO: docs
     */
    public operator fun plus(other: SculptorContractor): SculptorContractor

    /**
     * TODO: docs
     */
    public interface State {
        /**
         * TODO: docs
         */
        public val modifiers: PolymorphicModuleBuilder<ModifierContract>.() -> Unit

        /**
         * TODO: docs
         */
        public val layouts: PolymorphicModuleBuilder<LayoutContract>.() -> Unit

        /**
         * TODO: docs
         */
        public val states: PolymorphicModuleBuilder<StateContract>.() -> Unit

        /**
         * TODO: docs
         */
        public val contractBuilder: SerializersModuleBuilder.() -> Unit
    }

    /**
     * TODO: docs
     */
    public companion object Factory {
        /**
         * TODO: docs
         */
        public fun create(state: State): SculptorContractor = SculptorContractorImpl(
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

private class SculptorContractorImpl(
    private val serializers: SerializersModule,
) : SculptorContractor {
    private val format: StringFormat by lazy {
        Json {
            prettyPrint = true
            serializersModule = serializers
            ignoreUnknownKeys = true
        }
    }

    override fun decode(string: String): Scaffold = format.decodeFromString(string)

    override fun encode(scaffold: Scaffold): String = format.encodeToString(scaffold)

    override fun plus(other: SculptorContractor): SculptorContractor = when (other) {
        is SculptorContractorImpl -> SculptorContractorImpl(
            serializers = serializers + other.serializers
        )
    }
}
