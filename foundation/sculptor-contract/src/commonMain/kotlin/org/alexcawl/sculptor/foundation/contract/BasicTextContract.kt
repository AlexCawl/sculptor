package org.alexcawl.sculptor.foundation.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract

@Serializable
@SerialName("basic_text@contract")
data class BasicTextContract(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("modifiers")
    override val modifiers: List<ModifierContract>,
    @SerialName("text")
    val text: String,
    @SerialName("soft_wrap")
    val softWrap: Boolean,
    @SerialName("max_lines")
    val maxLines: Int,
    @SerialName("min_lines")
    val minLines: Int,
) : LayoutContract
