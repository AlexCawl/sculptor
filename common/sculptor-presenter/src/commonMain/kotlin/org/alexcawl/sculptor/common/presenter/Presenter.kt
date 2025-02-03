package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

abstract class Presenter<Input : Any, Output : Any> {
    abstract val input: KClass<Input>
    abstract val output: KClass<Output>

    @InternalSculptorApi
    @JvmName("internalTransform")
    @Suppress("UNCHECKED_CAST")
    fun transform(scope: PresenterScope, input: Any): Output = scope.transform(input as Input)

    abstract fun PresenterScope.transform(input: Input): Output
}
