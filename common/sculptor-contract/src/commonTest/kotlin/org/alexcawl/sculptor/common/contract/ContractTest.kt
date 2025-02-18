package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.json.Json
import kotlin.test.Test

abstract class ContractTest<V> {
    abstract val format: Json
    abstract val value: V
    abstract val string: String

    @Test
    abstract fun serializationTest()

    @Test
    abstract fun deserializationTest()
}
