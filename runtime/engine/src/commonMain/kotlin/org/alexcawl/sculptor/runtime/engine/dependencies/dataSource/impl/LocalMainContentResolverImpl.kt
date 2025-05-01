package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.sculptor.core.contract.SculptorContent
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolver
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.LocalContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.RemoteContentSource

internal class LocalMainContentResolverImpl(
    private val remoteContentSource: RemoteContentSource,
    private val localContentSource: LocalContentSource?,
) : ContentResolver {
    override val contentResolutionStrategy: ContentResolutionStrategy =
        ContentResolutionStrategy.LocalMain

    override fun resolve(key: String): Flow<Result<SculptorContent>> = flow {
        if (localContentSource != null) {
            localContentSource.select(key = key)
                .onSuccess { sculptorContent: SculptorContent ->
                    emit(Result.success(sculptorContent))
                }
                .onFailure { _: Throwable ->
                    remoteContentSource.fetch(key)
                        .onSuccess { sculptorContent: SculptorContent ->
                            emit(Result.success(sculptorContent))
                            localContentSource.save(sculptorContent = sculptorContent)
                        }
                        .onFailure { remoteSourceException: Throwable ->
                            emit(
                                Result.failure(
                                    ContentNotFoundException(
                                        message = "Sculptor content not found in remote source after local source failure",
                                        cause = remoteSourceException,
                                    )
                                )
                            )
                        }
                }
        } else {
            remoteContentSource.fetch(key)
                .onSuccess { sculptorContent: SculptorContent ->
                    emit(Result.success(sculptorContent))
                }
                .onFailure { remoteSourceException: Throwable ->
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
