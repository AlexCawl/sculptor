package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.contract.ValueContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public typealias PresenterProvider = (inputClass: KClass<out Any>, outputClass: KClass<out Any>) -> Presenter<*, *>

/**
 * TODO: docs
 */
public typealias BlockProvider = (id: Identifier) -> Block<*>

/**
 * TODO: docs
 */
public typealias ValueProvider = (id: Identifier) -> ValueContract

/**
 * TODO: docs
 */
@OptIn(InternalSculptorApi::class)
public data class PresenterScope @InternalSculptorApi constructor(
    private val presenterProvider: PresenterProvider,
    private val blockProvider: BlockProvider,
    private val valueProvider: ValueProvider,
) {
    /**
     * TODO: docs
     */
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
    public inline fun <reified Out : ValueContract> getValue(identifier: Identifier): Out {
        val value = internalGetValue(identifier)
        return value as? Out
            ?: error("Value with identifier $identifier is not of type ${Out::class.simpleName}")
    }

    /**
     * TODO: docs
     */
    public fun getLayout(identifier: Identifier): Layout {
        val block: Block<*> = blockProvider(identifier)
        val modifiers: List<ModifierContract> = block.modifiers
        val state: StateContract = block.states.find { it.id == block.state }
            ?: error("No state found for ${block.state}")
        val statePresenter: Presenter<*, *> = presenterProvider(state::class, Layout::class)
        return statePresenter.internalTransform(
            scope = this,
            input = StatePresenter.Bundle(
                id = identifier,
                modifiers = modifiers,
                state = state,
            ),
        ) as Layout
    }

    @InternalSculptorApi
    public fun internalMap(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
        value: Any
    ): Any {
        println("$inputClass -> $outputClass")
        return presenterProvider(inputClass, outputClass).also {
            println("$it for $value")
        }.internalTransform(
            scope = this,
            input = value
        )
    }

    @InternalSculptorApi
    public fun internalGetValue(identifier: Identifier): ValueContract = valueProvider(identifier)
}
