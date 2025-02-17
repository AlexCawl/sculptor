package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.ValueContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public typealias PresenterProvider = (inputClass: KClass<out Any>, outputClass: KClass<out Any>) -> Presenter<*, *>

/**
 * TODO: docs
 */
public typealias LayoutProvider = (id: Identifier) -> LayoutContract

/**
 * TODO: docs
 */
public typealias ValueProvider = (id: Identifier) -> ValueContract

/**
 * TODO: docs
 */
public data class PresenterScope @InternalSculptorApi constructor(
    private val presenterProvider: PresenterProvider,
    private val layoutProvider: LayoutProvider,
    private val valueProvider: ValueProvider,
) {
    /**
     * TODO: docs
     */
    @OptIn(InternalSculptorApi::class)
    public inline fun <reified In : Any, reified Out : Any> map(input: In): Out {
        return this.internalMap(
            inputClass = In::class,
            outputClass = Out::class,
            value = input
        ) as Out
    }

    /**
     * TODO: docs
     */
    public inline fun <reified In : Any, reified Out : Any> listMap(input: List<In>): List<Out> =
        input.map(::map)

    /**
     * TODO: docs
     */
    public fun modifierMap(input: List<ModifierContract>): Modifier =
        listMap<ModifierContract, Modifier>(input).fold(Modifier, Modifier::then)

    /**
     * TODO: docs
     */
    @OptIn(InternalSculptorApi::class)
    public inline fun <reified Out : LayoutContract> getLayout(identifier: Identifier): Out {
        val layout = internalGetLayout(identifier)
        return layout as? Out
            ?: error("Layout with identifier $identifier is not of type ${Out::class.simpleName}")
    }

    /**
     * TODO: docs
     */
    @OptIn(InternalSculptorApi::class)
    public inline fun <reified Out : ValueContract> getValue(identifier: Identifier): Out {
        val value = internalGetValue(identifier)
        return value as? Out
            ?: error("Value with identifier $identifier is not of type ${Out::class.simpleName}")
    }

    @InternalSculptorApi
    public fun internalMap(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
        value: Any
    ): Any = presenterProvider(inputClass, outputClass).internalTransform(
        scope = this,
        input = value
    )

    @InternalSculptorApi
    public fun internalGetLayout(identifier: Identifier): Any = layoutProvider(identifier)

    @InternalSculptorApi
    public fun internalGetValue(identifier: Identifier): Any = valueProvider(identifier)
}
