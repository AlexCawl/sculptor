package org.alexcawl.sculptor.engine.api.contentService

import kotlinx.coroutines.flow.Flow
import org.alexcawl.sculptor.common.contract.SculptorContent

public interface ContentService {
    public val contentResolutionStrategy: ContentResolutionStrategy

    public operator fun invoke(key: String): Flow<Result<SculptorContent>>
}
