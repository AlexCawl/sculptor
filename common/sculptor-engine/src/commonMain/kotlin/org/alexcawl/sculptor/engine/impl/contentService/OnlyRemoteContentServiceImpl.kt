package org.alexcawl.sculptor.engine.impl.contentService

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.sculptor.common.contract.SculptorContent
import org.alexcawl.sculptor.engine.api.contentService.ContentResolutionStrategy
import org.alexcawl.sculptor.engine.api.contentService.ContentService
import org.alexcawl.sculptor.engine.api.contentService.RemoteContentSource

internal class OnlyRemoteContentServiceImpl(
    private val remoteContentSource: RemoteContentSource,
) : ContentService {
    override val contentResolutionStrategy: ContentResolutionStrategy =
        ContentResolutionStrategy.OnlyRemote

    override fun invoke(key: String): Flow<Result<SculptorContent>> = flow {
        emit(remoteContentSource.fetch(key))
    }
}