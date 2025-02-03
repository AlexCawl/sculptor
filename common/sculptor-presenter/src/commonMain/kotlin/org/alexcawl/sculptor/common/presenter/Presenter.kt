package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

abstract class Presenter<Input : Any, Output : Any> {
    abstract val input: KClass<Input>
    abstract val output: KClass<Output>

    @InternalSculptorApi
    @JvmName("internalTransform")
    fun transform(scope: PresenterScope, input: Input): Output = scope.transform(input)

    abstract fun PresenterScope.transform(input: Input): Output
}
