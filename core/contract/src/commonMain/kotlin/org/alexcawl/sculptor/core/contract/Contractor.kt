package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.modules.PolymorphicModuleBuilder

public interface Contractor {
    public val stateContracts: PolymorphicModuleBuilder<StateContract>.() -> Unit

    public val modifierContracts: PolymorphicModuleBuilder<ModifierContract>.() -> Unit
}
