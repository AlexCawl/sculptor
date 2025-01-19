package org.alexcawl.skulptor.foundation.layout.box

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.core.factory.ContainerLayout
import org.alexcawl.skulptor.core.layout.ContainerLayoutFactory

@Serializable
@SerialName("layout@box")
data class BoxLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<@Contextual BaseModifier>,
    @Transient
    override val factory: ContainerLayoutFactory<BoxState> = BoxFactory,
) : ContainerLayout<BoxState>()
