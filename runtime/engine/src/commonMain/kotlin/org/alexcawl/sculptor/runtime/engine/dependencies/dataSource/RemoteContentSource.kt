package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource

import org.alexcawl.sculptor.core.contract.SculptorContent

public interface RemoteContentSource {
    public suspend fun fetch(key: String): Result<SculptorContent>
}
