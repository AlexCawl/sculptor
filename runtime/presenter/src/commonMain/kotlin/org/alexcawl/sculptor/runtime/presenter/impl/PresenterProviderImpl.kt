package org.alexcawl.sculptor.runtime.presenter.impl

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.runtime.presenter.PresenterProvider
import kotlin.reflect.KClass

public class PresenterProviderImpl(
    private val presenters: List<Presenter<*, *>>,
) : PresenterProvider {
    override suspend fun findPresenter(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
    ): Presenter<*, *> {
        return presenters
            .find { it.input == inputClass && it.output == outputClass }
            ?: error(message = "Cannot resolve presenter for input $inputClass and output $outputClass")
    }
}
