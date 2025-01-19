package org.alexcawl.skulptor.foundation.layout.column

import androidx.compose.ui.Alignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.provider.AlignmentProvider
import org.alexcawl.skulptor.provider.ArrangementProvider

@Serializable
@SerialName("state@column")
data class ColumnState(
    @SerialName("id")
    override val id: String,
    @SerialName("vertical_arrangement")
    val verticalArrangement: ArrangementProvider.Vertical = ArrangementProvider.Vertical.Top,
    @SerialName("horizontal_alignment")
    val horizontalAlignment: AlignmentProvider.Horizontal = AlignmentProvider.Horizontal(Alignment.Start),
    @SerialName("content")
    val content: List<String> = listOf()
) : BaseState
