package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.value.ValueContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

typealias PresenterProvider = (inputClass: KClass<out Any>, outputClass: KClass<out Any>) -> Presenter<*, *>

typealias LayoutProvider = (id: Identifier) -> LayoutContract

typealias ValueProvider = (id: Identifier) -> ValueContract

data class PresenterScope @InternalSculptorApi constructor(
    private val presenterProvider: PresenterProvider,
    private val layoutProvider: LayoutProvider,
    private val valueProvider: ValueProvider,
) {
    /**
     * Maps a value to an another value.
     * @param input The input value contract.
     * @return The mapped value.
     */
    @OptIn(InternalSculptorApi::class)
    inline fun <reified In : Any, reified Out : Any> map(input: In): Out {
        return this.internalMap(
            inputClass = In::class,
            outputClass = Out::class,
            value = input
        ) as Out
    }

    /**
     * Maps a list of values to an another values list.
     * @param input The input value contract of a list.
     * @return The mapped values list.
     */
    inline fun <reified In : Any, reified Out : Any> listMap(input: List<In>): List<Out> =
        input.map(::map)

    /**
     * Maps a list of modifiers to single [Modifier].
     * @param input The input list of modifiers.
     * @return The mapped [Modifier].
     */
    fun modifierMap(input: List<ModifierContract>): Modifier =
        listMap<ModifierContract, Modifier>(input).fold(Modifier, Modifier::then)

    @InternalSculptorApi
    fun internalMap(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
        value: Any
    ): Any = presenterProvider(inputClass, outputClass).internalTransform(
        scope = this,
        input = value
    )

    /**
     * Retrieves a [LayoutContract] from the given identifier
     * @param identifier The identifier of the [LayoutContract] to retrieve.
     * @return The [LayoutContract] with the given identifier.
     */
    @OptIn(InternalSculptorApi::class)
    inline fun <reified Out : LayoutContract> getLayout(identifier: Identifier): Out {
        val layout = internalGetLayout(identifier)
        return layout as? Out
            ?: error("Layout with identifier $identifier is not of type ${Out::class.simpleName}")
    }

    @InternalSculptorApi
    fun internalGetLayout(identifier: Identifier): Any = layoutProvider(identifier)

    /**
     * Retrieves a [ValueContract] from the given identifier
     * @param identifier The identifier of the [ValueContract] to get.
     * @return The [ValueContract]
     */
    @OptIn(InternalSculptorApi::class)
    inline fun <reified Out : ValueContract> getValue(identifier: Identifier): Out {
        val value = internalGetValue(identifier)
        return value as? Out
            ?: error("Value with identifier $identifier is not of type ${Out::class.simpleName}")
    }

    @InternalSculptorApi
    fun internalGetValue(identifier: Identifier): Any = valueProvider(identifier)
}
