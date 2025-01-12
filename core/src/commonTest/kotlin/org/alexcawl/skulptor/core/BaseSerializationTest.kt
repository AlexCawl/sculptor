package org.alexcawl.skulptor.core

import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.assertEquals

abstract class BaseSerializationTest {
    protected val format: StringFormat = Json

    protected inline fun <reified T> baseTest(
        string: String,
        factory: () -> T,
    ) {
        val serializable = factory()
        val serialized = format.encodeToString(serializable)
        assertEquals(
            expected = string,
            actual = serialized,
            message = "Wrong serialization result"
        )
        val deserialized = format.decodeFromString<T>(serialized)
        assertEquals(
            expected = serializable,
            actual = deserialized,
            message = "Wrong deserialization result"
        )
    }
}
