package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi

@Suppress(names = ["UNCHECKED_CAST", "EXTENSION_SHADOWED_BY_MEMBER"])
abstract class CommonPresenter<Input : Any, Output : Any> : Presenter<Input, Output> {
    @InternalSculptorApi
    override fun internalTransform(scope: PresenterScope, input: Any): Output = scope.transform(input as Input)

    protected abstract fun PresenterScope.transform(input: Input): Output
}
