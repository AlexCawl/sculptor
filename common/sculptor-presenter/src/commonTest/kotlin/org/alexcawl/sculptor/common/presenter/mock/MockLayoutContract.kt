package org.alexcawl.sculptor.common.presenter.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract

@Serializable
@SerialName("test@contract")
data class MockLayoutContract(
    override val id: Identifier,
    override val state: Identifier,
    override val modifiers: List<ModifierContract>,
    override val states: List<MockStateContract>
) : LayoutContract