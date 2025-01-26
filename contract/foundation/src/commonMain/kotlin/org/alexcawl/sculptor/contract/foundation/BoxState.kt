package org.alexcawl.sculptor.contract.foundation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.common.BaseState
import org.alexcawl.sculptor.contract.common.Alignment

@Serializable
@SerialName("state@box")
data class BoxState(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<BaseModifier>,
    @SerialName("content_alignment")
    val contentAlignment: Alignment.Both,
    @SerialName("propagate_min_constraints")
    val propagateMinConstraints: Boolean,
    @SerialName("content")
    val content: List<String>,
) : BaseState {
    companion object {
        operator fun invoke(
            id: String,
            modifiers: List<BaseModifier> = listOf(),
            contentAlignment: Alignment.Both = Alignment.Both.TopStart,
            propagateMinConstraints: Boolean = false,
            content: List<String>,
        ): BoxState = BoxState(
            id = id,
            modifiers = modifiers,
            contentAlignment = contentAlignment,
            propagateMinConstraints = propagateMinConstraints,
            content = content
        )
    }
}
