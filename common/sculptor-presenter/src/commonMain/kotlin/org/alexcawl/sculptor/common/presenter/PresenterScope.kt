package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

internal typealias DelegateTransform = (
    inputClass: KClass<Any>,
    outputClass: KClass<Any>,
    value: Any
) -> Any


class PresenterScope @InternalSculptorApi constructor(
    @PublishedApi
    internal val delegateTransform: DelegateTransform
) {
    @Suppress("UNCHECKED_CAST")
    inline fun <reified In : Any, reified Out : Any> PresenterScope.transform(input: In): Out {
        return this.delegateTransform(
            In::class as KClass<Any>,
            Out::class as KClass<Any>,
            input
        ) as Out
    }
}