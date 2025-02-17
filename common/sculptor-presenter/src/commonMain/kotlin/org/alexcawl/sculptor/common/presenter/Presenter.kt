package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public sealed interface Presenter<Input : Any, Output : Any> {
    /**
     * TODO: docs
     */
    public val input: KClass<Input>

    /**
     * TODO: docs
     */
    public val output: KClass<Output>

    @InternalSculptorApi
    public fun internalTransform(scope: PresenterScope, input: Any): Output
}
