package org.alexcawl.sculptor.common.builder.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ModifierContract

@Serializable
@SerialName("mock@modifier")
data class MockModifierContract(
    val testValue: String,
) : ModifierContract
