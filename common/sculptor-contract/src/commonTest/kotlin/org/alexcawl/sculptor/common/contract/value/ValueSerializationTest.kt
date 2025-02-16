package org.alexcawl.sculptor.common.contract.value

import kotlinx.serialization.json.Json
import org.alexcawl.sculptor.common.contract.BaseSerializationTest
import org.alexcawl.sculptor.common.contract.ValueContract
import kotlin.test.Test

abstract class ValueSerializationTest<V : ValueContract> : BaseSerializationTest<V>() {
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
