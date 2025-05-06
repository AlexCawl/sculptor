package org.alexcawl.sculptor.runtime.engine.dependencies

public interface LocalContentSource {
    public suspend fun find(key: String): String?

    public suspend fun save(key: String, content: String)
}
