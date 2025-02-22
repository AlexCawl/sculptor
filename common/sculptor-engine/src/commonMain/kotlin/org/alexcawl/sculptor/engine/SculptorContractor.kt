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
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.contract.ValueContract

/**
 * TODO: docs
 */
public sealed interface SculptorContractor {
    /**
     * TODO: docs
     */
    public val serializers: SerializersModule

    /**
     * TODO: docs
     */
    public fun decode(string: String) : Result<Scaffold>

    /**
     * TODO: docs
     */
    public fun encode(scaffold: Scaffold): Result<String>

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
        public val values: PolymorphicModuleBuilder<ValueContract>.() -> Unit

        /**
         * TODO: docs
         */
        public val modifiers: PolymorphicModuleBuilder<ModifierContract>.() -> Unit

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
                polymorphic(ValueContract::class, builderAction = state.values)
                polymorphic(ModifierContract::class, builderAction = state.modifiers)
                polymorphic(StateContract::class, builderAction = state.states)
                with(state) {
                    this@SerializersModule.contractBuilder()
                }
            },
        )
    }
}

private class SculptorContractorImpl(
    override val serializers: SerializersModule,
) : SculptorContractor {
    private val format: StringFormat by lazy {
        Json {
            prettyPrint = true
            serializersModule = serializers
            ignoreUnknownKeys = true
        }
    }

    override fun decode(string: String): Result<Scaffold> = runCatching {
        format.decodeFromString<Scaffold>(string)
    }

    override fun encode(scaffold: Scaffold): Result<String> = runCatching {
        format.encodeToString<Scaffold>(scaffold)
    }

    override fun plus(other: SculptorContractor): SculptorContractor = SculptorContractorImpl(
        serializers = serializers + other.serializers
    )
}
