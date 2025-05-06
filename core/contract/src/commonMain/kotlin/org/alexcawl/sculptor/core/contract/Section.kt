package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * Represents a section in the sculptor system.
 */
@Serializable
public sealed interface Section {
    /**
     * The unique identifier for the section.
     */
    @SerialName("id")
    public val id: Identifier

    /**
     * A list of modifiers associated with the section.
     */
    @SerialName("modifiers")
    public val modifiers: List<ModifierContract>

    /**
     * Represents a template section.
     */
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
        /**
         * Combines this template with another template.
         * Throws an IllegalArgumentException if the templates have different IDs.
         *
         * @param other The template to be combined with this one.
         * @return A new template with combined modifiers and content.
         */
        public operator fun plus(other: Template): Template {
            check(value = id == other.id) {
                "Cannot add two templates with different ids"
            }
            return Template(
                id = id,
                modifiers = modifiers + other.modifiers,
                content = JsonObject(content = content + other.content),
            )
        }
    }

    /**
     * Represents a block section.
     */
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
