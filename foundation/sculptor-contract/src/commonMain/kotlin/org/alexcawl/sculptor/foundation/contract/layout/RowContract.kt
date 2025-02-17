package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement

@Serializable
@SerialName("row@layout")
public data class RowLayoutContract(
    override val id: Identifier,
    override val state: Identifier,
    override val modifiers: List<ModifierContract>,
    override val states: List<RowStateContract>
) : LayoutContract

@Serializable
@SerialName("row@state")
public data class RowStateContract(
    override val id: Identifier,
    override val modifiers: List<ModifierContract>,
    @SerialName("horizontal_arrangement")
    val horizontalArrangement: Arrangement.Horizontal,
    @SerialName("vertical_alignment")
    val verticalAlignment: Alignment.Vertical,
    @SerialName("content")
    val content: List<Identifier>,
) : StateContract
