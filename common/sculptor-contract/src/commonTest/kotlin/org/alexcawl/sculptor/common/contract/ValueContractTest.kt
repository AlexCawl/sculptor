package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.KSerializer

abstract class ValueContractTest<VC : ValueContract> : ContractTest<VC>() {
    abstract override val value: VC
    abstract override val serializer: KSerializer<VC>
    abstract override val string: String
}
