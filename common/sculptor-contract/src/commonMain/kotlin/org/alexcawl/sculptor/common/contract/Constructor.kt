package org.alexcawl.sculptor.common.contract

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

public interface Constructor {
    public suspend fun construct(schema: Schema): Scaffold

    public companion object {
        public operator fun invoke(stringFormat: StringFormat): Constructor =
            ConstructorImpl(stringFormat)
    }
}

private class ConstructorImpl(
    private val stringFormat: StringFormat,
) : Constructor {
    override suspend fun construct(schema: Schema): Scaffold {
        val templates: List<Template> = schema.templates
        val components: List<Component> = coroutineScope {
            schema.sections.map { section: Section ->
                async {
                    when (section) {
                        is Component -> section
                        is Template -> {
                            val resultTemplate: Template = templates
                                .find { it.id == section.id }
                                ?.let { section + it }
                                ?: section
                            val encodedTemplate = stringFormat.encodeToString(value = resultTemplate)
                            stringFormat.decodeFromString<Component>(string = encodedTemplate)
                        }
                    }
                }
            }.awaitAll()
        }
        return Scaffold(
            components = components,
            root = schema.root,
        )
    }
}
