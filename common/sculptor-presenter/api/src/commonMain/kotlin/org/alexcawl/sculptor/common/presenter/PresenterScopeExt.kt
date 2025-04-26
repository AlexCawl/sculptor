package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.alexcawl.sculptor.common.contract.ModifierContract

/**
 * Maps an input object of type [In] to an output object of type [Out] using the [PresenterScope].
 *
 * @receiver the [PresenterScope] that provides the context for the transformation.
 * @param In The type of the input objects.
 * @param Out The type of the output objects.
 * @param input The input object to be mapped.
 * @return The mapped output object of type [Out].
 */
public suspend inline fun <reified In : Any, reified Out : Any> PresenterScope.map(input: In): Out {
    return this.map(
        inputClass = In::class,
        outputClass = Out::class,
        value = input,
    ) as Out
}

/**
 * Maps each element in a list of input objects of type [In] to a list of output objects of type [Out] using the [PresenterScope].
 * This function performs the mapping asynchronously.
 *
 * @receiver the [PresenterScope] that provides the context for the transformation.
 * @param In The type of the input objects.
 * @param Out The type of the output objects.
 * @param input The list of input objects to be mapped.
 * @return A list of mapped output objects of type [Out].
 */
public suspend inline fun <reified In : Any, reified Out : Any> PresenterScope.mapEach(input: List<In>): List<Out> {
    return coroutineScope {
        input.map { inputItem: In ->
            async(start = CoroutineStart.LAZY) {
                this@mapEach.map(
                    inputClass = inputItem::class,
                    outputClass = Out::class,
                    value = inputItem
                ) as Out
            }
        }.awaitAll()
    }
}

/**
 * Maps each element in a list of [ModifierContract] objects to a [Modifier] using the [PresenterScope].
 * This function performs the mapping asynchronously and combines all modifiers into a single [Modifier].
 *
 * @receiver the [PresenterScope] that provides the context for the transformation.
 * @param input The list of [ModifierContract] objects to be mapped.
 * @return A single [Modifier] that combines all mapped modifiers.
 */
public suspend fun PresenterScope.mapModifier(input: List<ModifierContract>): Modifier {
    return coroutineScope {
        input.map { inputModifier: ModifierContract ->
            async(start = CoroutineStart.LAZY) {
                this@mapModifier.map(
                    inputClass = inputModifier::class,
                    outputClass = Modifier::class,
                    value = inputModifier
                ) as Modifier
            }
        }.awaitAll()
    }.fold(
        initial = Modifier,
        operation = Modifier::then,
    )
}
