package org.alexcawl.sculptor.foundation.contract

import kotlinx.serialization.json.Json
import kotlin.test.Test

abstract class BaseSerializationTest<V> {
    protected open val format: Json = Json {
        prettyPrint = true
    }

    abstract val value: V
    abstract val string: String

    @Test
    abstract fun serializationTest()

    @Test
    abstract fun deserializationTest()
}
