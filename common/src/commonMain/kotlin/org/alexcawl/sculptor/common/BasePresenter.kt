package org.alexcawl.sculptor.common

import kotlin.reflect.KClass

interface BasePresenter<Input : Any, Output : Any> {
    val input: KClass<Input>
    val output: KClass<Output>

    fun transform(input: Input): Output
}

@Suppress("UNCHECKED_CAST")
inline fun <reified Input : Any, reified Output : Any> BasePresenter<*, *>.transform(input: Input): Output =
    (this as BasePresenter<Input, Output>).transform(input)
