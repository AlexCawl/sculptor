package org.alexcawl.sculptor.common.builder.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.builder.LayoutBuilder
import org.alexcawl.sculptor.common.builder.placer.LayoutPlacer
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract

@Serializable
@SerialName("mock@layout")
data class MockLayoutContract(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("state")
    override val state: Identifier,
    @SerialName("modifiers")
    override val modifiers: List<ModifierContract>,
    @SerialName("states")
    override val states: List<MockStateContract>
) : LayoutContract

fun LayoutPlacer.mockLayout(
    identifier: String,
    modifiers: List<ModifierContract> = emptyList(),
    layoutBuilderBlock: LayoutBuilder<MockLayoutContract, MockStateContract>.(Identifier, List<ModifierContract>, List<MockStateContract>) -> Unit,
): MockLayoutContract {
    val builder = LayoutBuilder.invoke(
        scope = this,
        identifier = identifier,
        modifiers = modifiers,
        layoutBuilderBlock = layoutBuilderBlock,
    )
}