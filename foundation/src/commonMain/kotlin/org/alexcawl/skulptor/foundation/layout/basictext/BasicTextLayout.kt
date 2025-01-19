package org.alexcawl.skulptor.foundation.layout.basictext

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.core.factory.ComponentLayout
import org.alexcawl.skulptor.core.layout.ComponentLayoutFactory

@Serializable
@SerialName("layout@basic_text")
data class BasicTextLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<@Contextual BaseModifier>,
    @Transient
    override val factory: ComponentLayoutFactory<BasicTextState> = BasicTextFactory,
) : ComponentLayout<BasicTextState>()
