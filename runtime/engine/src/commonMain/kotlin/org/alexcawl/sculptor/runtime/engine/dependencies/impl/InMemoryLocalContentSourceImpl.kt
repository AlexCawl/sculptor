package org.alexcawl.sculptor.runtime.engine.dependencies.impl

import org.alexcawl.sculptor.runtime.engine.dependencies.LocalContentSource

internal class InMemoryLocalContentSourceImpl : LocalContentSource {
    private val localContentMap: MutableMap<String, String> = mutableMapOf()

    override suspend fun find(key: String): String? {
        return localContentMap[key]
    }

    override suspend fun save(key: String, content: String) {
        localContentMap[key] = content
    }
}
