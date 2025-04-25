package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

public interface PresenterScope {
    public suspend fun map(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
        value: Any,
    ): Any

    public suspend fun layout(input: List<Identifier>): List<Layout>
}
