package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource

import org.alexcawl.sculptor.core.contract.SculptorContent

public interface LocalContentSource {
    public suspend fun select(key: String): Result<SculptorContent>

    public suspend fun save(sculptorContent: SculptorContent)
}
