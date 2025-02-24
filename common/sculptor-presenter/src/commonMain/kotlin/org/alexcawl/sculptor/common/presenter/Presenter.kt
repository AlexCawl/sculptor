package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public sealed class Presenter<Input : Any, Output : Any> {
    /**
     * TODO: docs
     */
    public abstract val input: KClass<Input>

    /**
     * TODO: docs
     */
    public abstract val output: KClass<Output>

    @InternalSculptorApi
    public abstract fun internalTransform(scope: PresenterScope, input: Any): Output
}
