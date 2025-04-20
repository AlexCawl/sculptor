package org.alexcawl.sculptor.engine.api.contentService

import org.alexcawl.sculptor.common.contract.SculptorContent

public interface LocalContentSource {
    public fun select(key: String): Result<SculptorContent>

    public suspend fun save(sculptorContent: SculptorContent)
}
