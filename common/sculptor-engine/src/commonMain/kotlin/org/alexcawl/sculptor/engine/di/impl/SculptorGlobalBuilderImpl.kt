package org.alexcawl.sculptor.engine.di.impl

import org.alexcawl.sculptor.common.contract.Contractor
import org.alexcawl.sculptor.common.di.DiComponent
import org.alexcawl.sculptor.common.di.DiTree
import org.alexcawl.sculptor.common.di.OverridableBuilder
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.engine.api.ExceptionHandler
import org.alexcawl.sculptor.engine.api.contentService.ContentResolutionStrategy
import org.alexcawl.sculptor.engine.api.contentService.ContentResolver
import org.alexcawl.sculptor.engine.api.contentService.LocalContentSource
import org.alexcawl.sculptor.engine.api.contentService.RemoteContentSource
import org.alexcawl.sculptor.engine.di.api.SculptorGlobalBuilder
import org.alexcawl.sculptor.engine.di.impl.module.contentServiceModule
import org.alexcawl.sculptor.engine.di.impl.module.presenterModule
import org.alexcawl.sculptor.engine.di.impl.module.rendererModule
import org.alexcawl.sculptor.engine.di.impl.module.contractorModule
import kotlin.reflect.KClass

public class SculptorGlobalBuilderImpl : SculptorGlobalBuilder, OverridableBuilder {
    private val diComponent: DiComponent = DiComponent()

    init {
        diComponent.addModules(
            contentServiceModule(),
            presenterModule(),
            rendererModule(),
            contractorModule(),
        )
    }

    override fun override(override: DiComponent.() -> Unit): Unit = override(diComponent)

    public fun build(): DiTree = DiTree(diComponent = diComponent)

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

    override fun exceptionHandler(exceptionHandler: () -> ExceptionHandler): Unit =
        diComponent.singleton(
            key = ExceptionHandler::class,
            type = ExceptionHandler::class,
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
}
