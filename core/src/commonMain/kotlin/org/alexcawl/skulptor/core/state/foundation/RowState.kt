package org.alexcawl.skulptor.core.state.foundation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SLayout
import org.alexcawl.skulptor.core.alignment.SAlignmentVertical
import org.alexcawl.skulptor.core.arrangement.SArrangementHorizontal
import org.alexcawl.skulptor.core.state.SkulptorState

@Serializable
@SerialName("state@row")
data class RowState(
    @SerialName("horizontal_arrangement")
    val horizontalArrangement: SArrangementHorizontal? = null,
    @SerialName("vertical_alignment")
    val verticalAlignment: SAlignmentVertical? = null,
    @SerialName("content")
    val content: List<SLayout>? = null
) : SkulptorState
