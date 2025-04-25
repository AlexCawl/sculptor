package org.alexcawl.sculptor.engine.dataSource

import org.alexcawl.sculptor.common.contract.SculptorContent

public interface RemoteContentSource {
    public suspend fun fetch(key: String): Result<SculptorContent>
}
