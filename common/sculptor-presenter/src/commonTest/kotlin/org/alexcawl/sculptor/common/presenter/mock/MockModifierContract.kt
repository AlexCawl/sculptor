package org.alexcawl.sculptor.common.presenter.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ModifierContract

@Serializable
@SerialName("mock@modifier")
data class MockModifierContract(
    @SerialName("value")
    val value: Mock,
) : ModifierContract
