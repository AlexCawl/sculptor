package org.alexcawl.sculptor.engine.impl

import kotlinx.coroutines.CoroutineExceptionHandler
import org.alexcawl.sculptor.common.contract.Contractor
import org.alexcawl.sculptor.internal.di.DiComponent
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.engine.SculptorGlobalBuilder
import org.alexcawl.sculptor.engine.dataSource.ContentResolutionStrategy
import org.alexcawl.sculptor.engine.dataSource.ContentResolver
import org.alexcawl.sculptor.engine.dataSource.LocalContentSource
import org.alexcawl.sculptor.engine.dataSource.RemoteContentSource
import kotlin.reflect.KClass

internal class SculptorGlobalBuilderImpl : SculptorGlobalBuilder, DiTreeBuilder {
    private val diComponent: DiComponent = DiComponent()

    override fun override(override: DiComponent.() -> Unit): Unit = override(diComponent)

    override fun <K : ContentResolver> contentResolver(
        key: KClass<K>,
        contentResolverProvider: () -> K
    ): Unit = diComponent.singleton(
        key = key,
        type = ContentResolver::class,
        factory = { contentResolverProvider() },
    )

    override fun <K : ContentResolutionStrategy> contentResolutionStrategy(
        key: KClass<K>,
        contentResolutionStrategy: () -> K
    ): Unit = diComponent.singleton(
        key = key,
        type = ContentResolutionStrategy::class,
        factory = { contentResolutionStrategy() },
    )

    override fun localContentSource(localContentSource: () -> LocalContentSource): Unit =
        diComponent.singleton(
            key = LocalContentSource::class,
            type = LocalContentSource::class,
            factory = { localContentSource() },
        )

    override fun remoteContentSource(remoteContentSource: () -> RemoteContentSource): Unit =
        diComponent.singleton(
            key = RemoteContentSource::class,
            type = RemoteContentSource::class,
            factory = { remoteContentSource() },
        )

    override fun exceptionHandler(exceptionHandler: () -> CoroutineExceptionHandler): Unit =
        diComponent.singleton(
            key = CoroutineExceptionHandler::class,
            type = CoroutineExceptionHandler::class,
            factory = { exceptionHandler() },
        )

    override fun <K : Renderer<*>> renderer(key: KClass<K>, renderer: () -> K): Unit =
        diComponent.singleton(
            key = key,
            type = Renderer::class,
            factory = { renderer() },
        )

    override fun <K : Presenter<*, *>> presenter(key: KClass<K>, presenter: () -> K): Unit =
        diComponent.singleton(
            key = key,
            type = Presenter::class,
            factory = { presenter() },
        )

    override fun <K : Contractor> contractor(key: KClass<K>, contractor: () -> K): Unit =
        diComponent.singleton(
            key = key,
            type = Contractor::class,
            factory = { contractor() },
        )

    override fun build(): DiTree = DiTree(diComponent = diComponent)
}
