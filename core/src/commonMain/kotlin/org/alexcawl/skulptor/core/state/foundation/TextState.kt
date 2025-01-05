package org.alexcawl.skulptor.core.state.foundation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.state.SkulptorState

@Serializable
@SerialName("state@text")
data class TextState(
    @SerialName("text")
    val text: String? = null,
    @SerialName("soft_wrap")
    val softWrap: Boolean? = null,
    @SerialName("max_lines")
    val maxLines: Int? = null,
    @SerialName("min_lines")
    val minLines: Int? = null
) : SkulptorState
