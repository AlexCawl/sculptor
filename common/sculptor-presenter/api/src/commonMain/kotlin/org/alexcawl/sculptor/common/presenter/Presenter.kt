package org.alexcawl.sculptor.common.presenter

import kotlin.reflect.KClass

public abstract class Presenter<Input : Any, Output : Any> {
    public abstract val input: KClass<Input>

    public abstract val output: KClass<Output>

    public suspend fun transform(scope: PresenterScope, any: Any): Output {
        @Suppress("UNCHECKED_CAST")
        return scope.transform(input = any as Input)
    }

    public abstract suspend fun PresenterScope.transform(input: Input): Output
}
