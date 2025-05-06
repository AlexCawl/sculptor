package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.modules.PolymorphicModuleBuilder

/**
 * Represents a contractor that defines serialization contracts for state and modifier contracts.
 *
 * @property stateContracts A lambda function that configures polymorphic serialization for [StateContract] instances.
 * @property modifierContracts A lambda function that configures polymorphic serialization for [ModifierContract] instances.
 */
public interface Contractor {
    /**
     * A lambda function that configures polymorphic serialization for [StateContract] instances.
     * This function is used to define how different implementations of [StateContract] should be serialized and deserialized.
     */
    public val stateContracts: PolymorphicModuleBuilder<StateContract>.() -> Unit

    /**
     * A lambda function that configures polymorphic serialization for [ModifierContract] instances.
     * This function is used to define how different implementations of [ModifierContract] should be serialized and deserialized.
     */
    public val modifierContracts: PolymorphicModuleBuilder<ModifierContract>.() -> Unit
}
