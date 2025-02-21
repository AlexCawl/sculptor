package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.KSerializer

abstract class ModifierContractTest<MC : ModifierContract> : ContractTest<MC>() {
    abstract override val value: MC
    abstract override val serializer: KSerializer<MC>
    abstract override val string: String
}
