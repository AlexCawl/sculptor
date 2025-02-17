package org.alexcawl.sculptor.common.presenter.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ModifierContract

@Serializable
@SerialName("test@modifier")
data class MockModifierContract(
    val testValue: String,
) : ModifierContract
