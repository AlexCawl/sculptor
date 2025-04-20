package org.alexcawl.sculptor.engine.impl.contentService

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.sculptor.common.contract.SculptorContent
import org.alexcawl.sculptor.engine.api.contentService.ContentResolutionStrategy
import org.alexcawl.sculptor.engine.api.contentService.ContentResolver
import org.alexcawl.sculptor.engine.api.contentService.LocalContentSource
import org.alexcawl.sculptor.engine.api.contentService.RemoteContentSource

internal class RemoteMainContentResolverImpl(
    private val remoteContentSource: RemoteContentSource,
    private val localContentSource: LocalContentSource?,
) : ContentResolver {
    override val contentResolutionStrategy: ContentResolutionStrategy =
        ContentResolutionStrategy.RemoteMain

    override fun resolve(key: String): Flow<Result<SculptorContent>> = flow {
        remoteContentSource.fetch(key)
            .onSuccess { sculptorContent: SculptorContent ->
                emit(Result.success(sculptorContent))
                localContentSource?.save(sculptorContent = sculptorContent)
            }
            .onFailure { remoteSourceException: Throwable ->
                if (localContentSource != null) {
                    localContentSource.select(key = key)
                        .onSuccess { sculptorContent: SculptorContent ->
                            emit(Result.success(sculptorContent))
                        }
                        .onFailure { localSourceException: Throwable ->
                            emit(
                                Result.failure(
                                    ContentNotFoundException(
                                        message = "Sculptor content not found in local source after remote source failure",
                                        cause = localSourceException,
                                    )
                                )
                            )
                        }
                } else {
                    emit(
                        Result.failure(
                            ContentNotFoundException(
                                message = "Sculptor content not found in remote source and localSource does not exists",
                                cause = remoteSourceException,
                            )
                        )
                    )
                }
            }
    }
}