package org.alexcawl.skulptor.foundation.layout.row

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.core.factory.ContainerLayout
import org.alexcawl.skulptor.core.layout.ContainerLayoutFactory

@Serializable
@SerialName("layout@row")
data class RowLayout(
    override val id: String,
    override val modifiers: List<@Contextual BaseModifier>,
    @Transient
    override val factory: ContainerLayoutFactory<RowState> = RowFactory,
) : ContainerLayout<RowState>()
