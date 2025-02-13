package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

interface Presenter<Input : Any, Output : Any> {
    val input: KClass<Input>
    val output: KClass<Output>

    @InternalSculptorApi
    fun internalTransform(scope: PresenterScope, input: Any): Output
}
