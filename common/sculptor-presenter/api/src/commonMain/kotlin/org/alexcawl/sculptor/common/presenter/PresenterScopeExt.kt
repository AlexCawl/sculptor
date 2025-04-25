package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.alexcawl.sculptor.common.contract.ModifierContract

public suspend inline fun <reified In : Any, reified Out : Any> PresenterScope.map(input: In): Out {
    return this.map(
        inputClass = In::class,
        outputClass = Out::class,
        value = input,
    ) as Out
}

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
