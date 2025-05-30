package org.alexcawl.sculptor.core.contractor

import kotlinx.serialization.modules.PolymorphicModuleBuilder
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.core.contract.StateContract
import kotlin.reflect.KClass

public interface ContractBundle {
    public val stateContracts: PolymorphicModuleBuilder<StateContract>.() -> Unit

    public val modifierContracts: PolymorphicModuleBuilder<ModifierContract>.() -> Unit

    public interface Consumer {
        public fun <K : ContractBundle> contract(key: KClass<K>, contractor: () -> K)
    }
}

public inline fun <reified K : ContractBundle> ContractBundle.Consumer.contract(noinline contract: () -> K) {
    contract(key = K::class, contractor = contract)
}
