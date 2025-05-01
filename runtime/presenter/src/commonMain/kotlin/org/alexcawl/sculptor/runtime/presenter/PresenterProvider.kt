package org.alexcawl.sculptor.runtime.presenter

import org.alexcawl.sculptor.core.presenter.Presenter
import kotlin.reflect.KClass

/**
 * Interface for providing presenters based on input and output class types.
 *
 * This functional interface defines a single method [findPresenter] that is used
 * to retrieve a presenter suitable for handling specific input and output types.
 */
public fun interface PresenterProvider {
    /**
     * Finds and returns a presenter for the given input and output class types.
     *
     * @param inputClass The class type of the input data.
     * @param outputClass The class type of the output data.
     * @return A presenter that can handle the specified input and output types.
     * @throws IllegalStateException if no suitable presenter is found for the given classes.
     */
    @Throws(IllegalStateException::class)
    public suspend fun findPresenter(inputClass: KClass<out Any>, outputClass: KClass<out Any>): Presenter<*, *>
}
