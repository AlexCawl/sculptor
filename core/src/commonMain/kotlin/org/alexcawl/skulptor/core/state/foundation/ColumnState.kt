package org.alexcawl.skulptor.core.state.foundation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SLayout
import org.alexcawl.skulptor.core.alignment.SAlignmentHorizontal
import org.alexcawl.skulptor.core.arrangement.SArrangementVertical
import org.alexcawl.skulptor.core.state.SkulptorState

@Serializable
@SerialName("state@column")
data class ColumnState(
    @SerialName("vertical_arrangement")
    val verticalArrangement: SArrangementVertical? = null,
    @SerialName("horizontal_alignment")
    val horizontalAlignment: SAlignmentHorizontal? = null,
    @SerialName("content")
    val content: List<SLayout>? = null
) : SkulptorState
