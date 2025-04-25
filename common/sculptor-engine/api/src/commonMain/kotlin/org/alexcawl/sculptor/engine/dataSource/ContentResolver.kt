package org.alexcawl.sculptor.engine.dataSource

import kotlinx.coroutines.flow.Flow
import org.alexcawl.sculptor.common.contract.SculptorContent

public interface ContentResolver {
    public val contentResolutionStrategy: ContentResolutionStrategy

    public fun resolve(key: String): Flow<Result<SculptorContent>>
}
