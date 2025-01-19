package org.alexcawl.skulptor.foundation.layout.basictext

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseState

@Serializable
@SerialName("state@basic_text")
data class BasicTextState(
    @SerialName("id")
    override val id: String,
    @SerialName("text")
    val text: String,
    @SerialName("soft_wrap")
    val softWrap: Boolean = true,
    @SerialName("max_lines")
    val maxLines: Int = Int.MAX_VALUE,
    @SerialName("min_lines")
    val minLines: Int = 1,
) : BaseState
