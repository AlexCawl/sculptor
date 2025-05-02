package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.impl

import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.LocalContentSource

internal class InMemoryLocalContentSourceImpl : LocalContentSource {
    private val lock: Any = Any()
    private val localContentMap: MutableMap<String, String> = mutableMapOf()

    override suspend fun find(key: String): String? =
        synchronized(lock) {
            localContentMap[key]
        }

    override suspend fun save(key: String, content: String) =
        synchronized(lock) {
            localContentMap[key] = content
        }
}
