@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi

abstract class CommonPresenter<Input : Any, Output : Any> : Presenter<Input, Output> {
    @InternalSculptorApi
    final override fun internalTransform(scope: PresenterScope, input: Any): Output = scope.transform(input as Input)

    protected abstract fun PresenterScope.transform(input: Input): Output
}
