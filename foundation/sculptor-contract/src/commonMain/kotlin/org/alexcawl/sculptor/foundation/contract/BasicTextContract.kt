package org.alexcawl.sculptor.foundation.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract
import org.alexcawl.sculptor.common.contract.value.StringValueContract

@Serializable
@SerialName("basic_text@layout")
data class BasicTextLayoutContract(
    override val id: Identifier,
    override val state: Identifier,
    override val modifiers: List<ModifierContract>,
    override val states: List<BasicTextStateContract>
) : LayoutContract

@Serializable
@SerialName("basic_text@state")
data class BasicTextStateContract(
    override val id: Identifier,
    override val modifiers: List<ModifierContract>,
    @SerialName("text")
    val text: StringValueContract,
    @SerialName("soft_wrap")
    val softWrap: Boolean,
    @SerialName("max_lines")
    val maxLines: Int,
    @SerialName("min_lines")
    val minLines: Int,
) : StateContract
