package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.KSerializer

abstract class StateContractTest<SC : StateContract> : ContractTest<SC>() {
    abstract override val value: SC
    abstract override val serializer: KSerializer<SC>
    abstract override val string: String
}
