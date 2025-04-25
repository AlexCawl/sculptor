package org.alexcawl.sculptor.engine.dataSource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.sculptor.common.contract.SculptorContent

internal class OnlyRemoteContentResolverImpl(
    private val remoteContentSource: RemoteContentSource,
) : ContentResolver {
    override val contentResolutionStrategy: ContentResolutionStrategy =
        ContentResolutionStrategy.OnlyRemote

    override fun resolve(key: String): Flow<Result<SculptorContent>> = flow {
        emit(remoteContentSource.fetch(key))
    }
}