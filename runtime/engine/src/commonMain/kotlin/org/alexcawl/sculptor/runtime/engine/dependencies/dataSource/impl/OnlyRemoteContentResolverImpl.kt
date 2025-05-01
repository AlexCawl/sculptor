package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.sculptor.core.contract.SculptorContent
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolver
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.RemoteContentSource

internal class OnlyRemoteContentResolverImpl(
    private val remoteContentSource: RemoteContentSource,
) : ContentResolver {
    override val contentResolutionStrategy: ContentResolutionStrategy =
        ContentResolutionStrategy.OnlyRemote

    override fun resolve(key: String): Flow<Result<SculptorContent>> = flow {
        emit(remoteContentSource.fetch(key))
    }
}