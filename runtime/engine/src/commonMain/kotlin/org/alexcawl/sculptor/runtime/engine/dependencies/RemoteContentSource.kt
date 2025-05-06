package org.alexcawl.sculptor.runtime.engine.dependencies

public interface RemoteContentSource {
    public suspend fun fetch(url: String): Result<String>
}
