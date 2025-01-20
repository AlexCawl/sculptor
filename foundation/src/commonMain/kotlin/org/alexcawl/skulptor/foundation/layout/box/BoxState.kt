package org.alexcawl.skulptor.foundation.layout.box

import androidx.compose.ui.Alignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.state.BaseState
import org.alexcawl.skulptor.core.state.CompositeState
import org.alexcawl.skulptor.provider.AlignmentProvider

@Serializable
@SerialName("state@box")
data class BoxState(
    @SerialName("id")
    override val id: String,
    @SerialName("content_alignment")
    val contentAlignment: AlignmentProvider.HorizontalAndVertical = AlignmentProvider.HorizontalAndVertical(
        Alignment.TopStart
    ),
    @SerialName("propagate_min_constraints")
    val propagateMinConstraints: Boolean = false,
    @SerialName("content")
    override val content: List<String> = listOf(),
) : CompositeState()
