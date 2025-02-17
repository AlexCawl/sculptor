package org.alexcawl.sculptor.common.presenter.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract

@Serializable
@SerialName("test@state")
data class MockStateContract(
    override val id: Identifier,
    override val modifiers: List<ModifierContract>,
    val testValue: String,
) : StateContract