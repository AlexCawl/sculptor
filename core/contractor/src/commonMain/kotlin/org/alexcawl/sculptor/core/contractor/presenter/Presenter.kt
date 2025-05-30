package org.alexcawl.sculptor.core.contractor.presenter

import kotlin.reflect.KClass

/**
 * An abstract class representing a presenter that transforms input data of type [Input] to output data of type [Output].
 *
 * @param Input the type of the input data that the presenter will process.
 * @param Output the type of the output data that the presenter will produce.
 */
public abstract class Presenter<Input : Any, Output : Any> {
    /**
     * The [KClass] representing the type of the input data.
     * This property must be implemented by subclasses to specify the input type.
     */
    public abstract val input: KClass<Input>

    /**
     * The [KClass] representing the type of the output data.
     * This property must be implemented by subclasses to specify the output type.
     */
    public abstract val output: KClass<Output>

    /**
     * Transforms the given input data into output data using the provided [PresenterScope].
     *
     * @param scope the [PresenterScope] that provides the context for the transformation.
     * @param any the input data to be transformed.
     * @return the transformed output data.
     * @throws ClassCastException if the provided input data cannot be cast to the expected [Input] type.
     */
    public suspend fun transform(scope: PresenterScope, any: Any): Output {
        @Suppress("UNCHECKED_CAST")
        return scope.dslTransform(input = any as Input)
    }

    /**
     * Abstract method to be implemented by subclasses to perform the actual transformation of input data to output data.
     *
     * @receiver the [PresenterScope] that provides the context for the transformation.
     * @param input the input data to be transformed.
     * @return the transformed output data.
     */
    public abstract suspend fun PresenterScope.dslTransform(input: Input): Output
}
