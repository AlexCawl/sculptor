package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.core.Logger
import org.alexcawl.sculptor.common.core.Tag

@Serializable
public sealed interface Section {
    public val id: Identifier
    public val modifiers: List<ModifierContract>
    public val state: StateContract

    @Serializable
    @SerialName("single")
    public data class Single(
        @SerialName("id")
        override val id: Identifier,

        @SerialName("modifiers")
        override val modifiers: List<ModifierContract>,

        @SerialName("state")
        override val state: StateContract
    ) : Section

    @Serializable
    @SerialName("composite")
    public data class Composite(
        @SerialName("id")
        public override val id: Identifier,

        @SerialName("modifiers")
        public override val modifiers: List<ModifierContract>,

        @SerialName("states")
        public val states: List<StateContract>,

        @SerialName("forced")
        public val forcedState: Identifier? = null,
    ) : Section {
        override val state: StateContract
            get() = states.find { it.id == forcedState }
                ?: states.firstOrNull()
                ?: Logger.e(
                    tag = Tag.SECTION,
                    message = "No state found for $this"
                )
    }
}
