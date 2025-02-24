@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi

/**
 * TODO: docs
 */
public abstract class CommonPresenter<Input : Any, Output : Any> : Presenter<Input, Output>() {
    @InternalSculptorApi
    public final override fun internalTransform(scope: PresenterScope, input: Any): Output = scope.transform(input as Input)

    /**
     * TODO: docs
     */
    protected abstract fun PresenterScope.transform(input: Input): Output
}
