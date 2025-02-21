package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.KSerializer

abstract class LayoutContractTest<LC : LayoutContract> : ContractTest<LC>() {
    abstract override val value: LC
    abstract override val serializer: KSerializer<LC>
    abstract override val string: String
}