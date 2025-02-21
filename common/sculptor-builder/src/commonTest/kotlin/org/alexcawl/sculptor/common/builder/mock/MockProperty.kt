package org.alexcawl.sculptor.common.builder.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MockProperty(
    @SerialName("test_value")
    val testValue: String,
)
