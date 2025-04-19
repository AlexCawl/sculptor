package org.alexcawl.sculptor.engine.api.contentService

import org.alexcawl.sculptor.common.contract.SculptorContent

public interface RemoteContentSource {
    public fun fetch(key: String): Result<SculptorContent>
}
