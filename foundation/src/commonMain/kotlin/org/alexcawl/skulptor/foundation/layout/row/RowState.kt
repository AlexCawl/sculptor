package org.alexcawl.skulptor.foundation.layout.row

import androidx.compose.ui.Alignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.provider.AlignmentProvider
import org.alexcawl.skulptor.provider.ArrangementProvider

@Serializable
@SerialName("state@row")
data class RowState(
    @SerialName("id")
    override val id: String,
    @SerialName("horizontal_arrangement")
    val horizontalArrangement: ArrangementProvider.Horizontal = ArrangementProvider.Horizontal.Start,
    @SerialName("vertical_alignment")
    val verticalAlignment: AlignmentProvider.Vertical = AlignmentProvider.Vertical(Alignment.Top),
    @SerialName("content")
    val content: List<String> = listOf()
) : BaseState
