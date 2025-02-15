package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModuleBuilder
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract

interface ContractorState {
    val modifiers: PolymorphicModuleBuilder<ModifierContract>.() -> Unit
    val layouts: PolymorphicModuleBuilder<LayoutContract>.() -> Unit
    val states: PolymorphicModuleBuilder<StateContract>.() -> Unit
    val contractBuilder: SerializersModuleBuilder.() -> Unit
}
