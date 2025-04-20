package org.alexcawl.sculptor.engine.impl.contentService

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.sculptor.common.contract.SculptorContent
import org.alexcawl.sculptor.engine.api.contentService.ContentResolutionStrategy
import org.alexcawl.sculptor.engine.api.contentService.ContentResolver
import org.alexcawl.sculptor.engine.api.contentService.RemoteContentSource

internal class OnlyRemoteContentResolverImpl(
    private val remoteContentSource: RemoteContentSource,
) : ContentResolver {
    override val contentResolutionStrategy: ContentResolutionStrategy =
        ContentResolutionStrategy.OnlyRemote

    override fun resolve(key: String): Flow<Result<SculptorContent>> = flow {
        emit(remoteContentSource.fetch(key))
    }
}