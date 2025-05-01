package org.alexcawl.sculptor.core.presenter

import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.layout.Layout
import kotlin.reflect.KClass

/**
 * Interface defining the contract for a presenter that can map data between different types
 * and generate layouts based on identifiers.
 */
public interface PresenterScope {
    /**
     * Maps an input value from one type to another.
     *
     * @param inputClass  the class of the input value
     * @param outputClass the class of the output value
     * @param value       the input value to be mapped
     * @return the mapped value of type [outputClass]
     */
    public suspend fun map(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>,
        value: Any,
    ): Any

    /**
     * Generates a list of layouts based on a list of identifiers.
     *
     * @param input a list of identifiers
     * @return a list of layouts corresponding to the identifiers
     */
    public suspend fun layout(input: List<Identifier>): List<Layout>
}
