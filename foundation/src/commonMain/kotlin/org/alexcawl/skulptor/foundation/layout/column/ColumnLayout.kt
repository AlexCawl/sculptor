package org.alexcawl.skulptor.foundation.layout.column

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.core.factory.ContainerLayout
import org.alexcawl.skulptor.core.layout.ContainerLayoutFactory

@Serializable
@SerialName("layout@column")
data class ColumnLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<@Contextual BaseModifier>,
    @Transient
    override val factory: ContainerLayoutFactory<ColumnState> = ColumnFactory,
) : ContainerLayout<ColumnState>()
