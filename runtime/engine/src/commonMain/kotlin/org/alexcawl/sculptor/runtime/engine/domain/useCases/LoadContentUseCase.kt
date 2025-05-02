package org.alexcawl.sculptor.runtime.engine.domain.useCases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.alexcawl.sculptor.runtime.engine.dependencies.coroutine.SculptorDispatchers
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolutionStrategy.LocalFirst
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolutionStrategy.RemoteFirst
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.LocalContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.RemoteContentSource
import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand.LoadContentCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import kotlin.reflect.KClass

internal class LoadContentUseCase(
    private val localContentSource: LocalContentSource,
    private val remoteContentSource: RemoteContentSource,
    private val contentResolutionStrategy: ContentResolutionStrategy,
    dispatchers: SculptorDispatchers,
) : SculptorUseCase<LoadContentCommand>() {
    override val type: KClass<LoadContentCommand> = LoadContentCommand::class

    override suspend fun TaskBuilder.handle(command: LoadContentCommand) {
        val (key: String, url: String) = command
        when (contentResolutionStrategy) {
            RemoteFirst -> remoteFirst(url = url)
            LocalFirst -> localFirst(key = key, url = url)
        }.onSuccess { content: String ->
            dispatch {
                SculptorEvent.HandleRawContentEvent(rawContent = content)
            }
        }.onFailure { exception: Throwable ->
            dispatch {
                SculptorEvent.FailureEvent(cause = exception)
            }
        }
    }

    private val scope: CoroutineScope = CoroutineScope(dispatchers.io)

    private suspend fun remoteFirst(url: String): Result<String> {
        return remoteContentSource.fetch(url = url)
    }

    private suspend fun localFirst(key: String, url: String): Result<String> {
        val localContent: String? = localContentSource.find(key = key)
        val remoteContent: Deferred<Result<String>> = scope.async {
            remoteContentSource.fetch(url = url)
        }
        return when (localContent) {
            null -> remoteContent.await()
            else -> Result.success(value = localContent)
        }
    }
}
