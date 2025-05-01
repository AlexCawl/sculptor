package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource

import kotlinx.coroutines.flow.Flow
import org.alexcawl.sculptor.core.contract.SculptorContent

public interface ContentResolver {
    public val contentResolutionStrategy: ContentResolutionStrategy

    public fun resolve(key: String): Flow<Result<SculptorContent>>
}
