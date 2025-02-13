package org.alexcawl.sculptor.common.presenter.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract

@Serializable
@SerialName("test@state")
data class TestStateContract(
    override val id: Identifier,
    override val modifiers: List<ModifierContract>,
    val testValue: String,
) : StateContract