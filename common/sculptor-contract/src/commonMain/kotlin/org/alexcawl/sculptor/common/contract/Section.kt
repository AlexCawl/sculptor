package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public sealed interface Section {
    @SerialName("id")
    public val id: Identifier

    @SerialName("modifiers")
    public val modifiers: List<ModifierContract>
}

@Serializable
public data class Template(
    public override val id: Identifier,
    public override val modifiers: List<ModifierContract>,
    public val content: JsonObject,
) : Section {
    public operator fun plus(other: Template): Template {
        check(value = id == other.id) {
            "Cannot add two templates with the different id"
        }
        return Template(
            id = id,
            modifiers = modifiers + other.modifiers,
            content = JsonObject(content = content + other.content),
        )
    }
}

@Serializable
public data class Component(
    public override val id: Identifier,
    public override val modifiers: List<ModifierContract>,
    public val state: StateContract
) : Section
