package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.impl

import org.alexcawl.sculptor.core.contract.SculptorContent
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.LocalContentSource

internal class DefaultLocalContentSource : LocalContentSource {
    private val lock: Any = Any()
    private val localContentMap: MutableMap<String, SculptorContent> = mutableMapOf()

    override suspend fun find(key: String): SculptorContent? =
        synchronized(lock) {
            localContentMap[key]
        }

    override suspend fun save(key: String, sculptorContent: SculptorContent): Unit =
        synchronized(lock) {
            localContentMap[key] = sculptorContent
        }
}
