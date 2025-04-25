package org.alexcawl.sculptor.engine.dataSource

import org.alexcawl.sculptor.common.contract.SculptorContent

public interface LocalContentSource {
    public suspend fun select(key: String): Result<SculptorContent>

    public suspend fun save(sculptorContent: SculptorContent)
}
