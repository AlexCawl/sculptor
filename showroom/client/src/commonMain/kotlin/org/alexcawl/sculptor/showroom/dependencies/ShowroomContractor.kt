package org.alexcawl.sculptor.showroom.dependencies

import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.subclass
import org.alexcawl.sculptor.core.contract.Contractor
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.showroom.components.ContainerState
import org.alexcawl.sculptor.showroom.components.TextState

internal data object ShowroomContractor : Contractor {
    override val stateContracts: PolymorphicModuleBuilder<StateContract>.() -> Unit = {
        subclass(TextState::class)
        subclass(ContainerState::class)
    }

    override val modifierContracts: PolymorphicModuleBuilder<ModifierContract>.() -> Unit = {

    }
}
