package org.alexcawl.sculptor.common.presenter

import kotlin.reflect.KClass

public fun interface PresenterProvider {
    public suspend operator fun invoke(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>
    ): Presenter<*, *>

    public companion object {
        public operator fun invoke(presenters: List<Presenter<*, *>>): PresenterProvider =
            PresenterProvider { inputClass: KClass<out Any>, outputClass: KClass<out Any> ->
                presenters.find { it.input == inputClass && it.output == outputClass }
                    ?: error(message = "Cannot resolve presenter for input $inputClass and output $outputClass")
            }
    }
}
