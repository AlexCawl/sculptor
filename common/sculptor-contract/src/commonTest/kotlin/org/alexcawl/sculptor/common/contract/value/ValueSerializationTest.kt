package org.alexcawl.sculptor.common.contract.value

import kotlinx.serialization.json.Json
import org.alexcawl.sculptor.common.contract.ContractTest
import org.alexcawl.sculptor.common.contract.ValueContract
import kotlin.test.Test

abstract class ValueSerializationTest<V : ValueContract> : ContractTest<V>() {
    override val format: Json = Json {
        prettyPrint = true
    }

    abstract override val value: V
    abstract override val string: String

    @Test
    abstract override fun serializationTest()

    @Test
    abstract override fun deserializationTest()
}
