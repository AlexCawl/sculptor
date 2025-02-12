package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

@Suppress(names = ["UNCHECKED_CAST", "EXTENSION_SHADOWED_BY_MEMBER"])
abstract class Presenter<Input : Any, Output : Any> {
    abstract val input: KClass<Input>
    abstract val output: KClass<Output>

    @InternalSculptorApi
    @JvmName("internalTransform")
    fun transform(scope: PresenterScope, input: Any): Output = scope.transform(input as Input)

    protected abstract fun PresenterScope.transform(input: Input): Output
}
