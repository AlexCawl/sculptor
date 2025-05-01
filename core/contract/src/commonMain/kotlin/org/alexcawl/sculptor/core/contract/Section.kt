package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public sealed interface Section {
    @SerialName("id")
    public val id: Identifier
    @SerialName("modifiers")
    public val modifiers: List<ModifierContract>

    @Serializable
    @SerialName("template")
    public data class Template(
        @SerialName("id")
        public override val id: Identifier,
        @SerialName("modifiers")
        public override val modifiers: List<ModifierContract>,
        @SerialName("content")
        public val content: JsonObject,
    ) : Section {
        public operator fun plus(other: Template): Template {
            check(value = (id == other.id)) { "Cannot add two templates with different ids" }
            return Template(
                id = id,
                modifiers = modifiers + other.modifiers,
                content = JsonObject(content = content + other.content),
            )
        }
    }

    @Serializable
    @SerialName("block")
    public data class Block(
        @SerialName("id")
        public override val id: Identifier,
        @SerialName("modifiers")
        public override val modifiers: List<ModifierContract>,
        @SerialName("state")
        public val state: StateContract
    ) : Section

}
