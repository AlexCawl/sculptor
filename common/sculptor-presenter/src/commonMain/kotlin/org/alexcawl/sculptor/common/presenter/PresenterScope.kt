package org.alexcawl.sculptor.common.presenter

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

typealias DelegateTransform = (
    inputClass: KClass<out Any>,
    outputClass: KClass<out Any>,
    value: Any
) -> Any

@Immutable
data class PresenterScope @InternalSculptorApi constructor(
    private val delegateTransform: DelegateTransform
) {
    @PublishedApi
    internal fun map(inputClass: KClass<out Any>, outputClass: KClass<out Any>, value: Any): Any {
        return delegateTransform(inputClass, outputClass, value)
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified In : Any, reified Out : Any> map(input: In): Out {
        return this.map(
            In::class as KClass<Any>,
            Out::class as KClass<Any>,
            input
        ) as Out
    }

    inline fun <reified In : Any, reified Out : Any> listMap(input: List<In>): List<Out> =
        input.map(::map)
}
