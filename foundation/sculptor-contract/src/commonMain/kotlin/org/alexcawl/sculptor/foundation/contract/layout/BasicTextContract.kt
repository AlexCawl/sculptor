package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract

@Serializable
@SerialName("basic_text@layout")
data class BasicTextLayoutContract(
    override val id: Identifier,
    override val state: Identifier,
    override val modifiers: List<ModifierContract>,
    override val states: List<BasicTextStateContract>
) : LayoutContract

@Serializable
sealed interface BasicTextStateContract : StateContract {
    @SerialName("soft_wrap")
    val softWrap: Boolean

    @SerialName("max_lines")
    val maxLines: Int

    @SerialName("min_lines")
    val minLines: Int

    @Serializable
    @SerialName("basic_text@state@dynamic")
    data class Dynamic(
        override val id: Identifier,
        override val modifiers: List<ModifierContract>,
        override val softWrap: Boolean,
        override val maxLines: Int,
        override val minLines: Int,
        @SerialName("text")
        val textIdentifier: Identifier,
    ) : BasicTextStateContract

    @Serializable
    @SerialName("basic_text@state@static")
    data class Static(
        override val id: Identifier,
        override val modifiers: List<ModifierContract>,
        override val softWrap: Boolean,
        override val maxLines: Int,
        override val minLines: Int,
        @SerialName("text")
        val text: String,
    ) : BasicTextStateContract
}
