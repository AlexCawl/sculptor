package org.alexcawl.sculptor.engine.di.impl

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.di.DiComponent
import org.alexcawl.sculptor.common.di.DiTree
import org.alexcawl.sculptor.common.di.OverridableBuilder
import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.engine.api.ExceptionHandler
import org.alexcawl.sculptor.engine.api.contentService.ContentResolutionStrategy
import org.alexcawl.sculptor.engine.api.contentService.ContentResolver
import org.alexcawl.sculptor.engine.api.contentService.LocalContentSource
import org.alexcawl.sculptor.engine.api.contentService.RemoteContentSource
import org.alexcawl.sculptor.engine.di.SculptorGlobalBuilder
import org.alexcawl.sculptor.engine.di.module.contentServiceModule
import org.alexcawl.sculptor.engine.di.module.presenterModule
import org.alexcawl.sculptor.engine.di.module.rendererModule
import org.alexcawl.sculptor.engine.di.module.serializerModule

public class SculptorGlobalBuilderImpl : SculptorGlobalBuilder, OverridableBuilder {
    private val diComponent: DiComponent = DiComponent()

    init {
        diComponent.addModules(
            contentServiceModule(),
            presenterModule(),
            rendererModule(),
            serializerModule(),
        )
    }

    override fun contentResolver(contentResolverProvider: () -> ContentResolver) {
        diComponent.singleton(ContentResolver::class) { contentResolverProvider() }
    }

    override fun contentResolutionStrategy(contentResolutionStrategy: () -> ContentResolutionStrategy) {
        diComponent.singleton(ContentResolutionStrategy::class) { contentResolutionStrategy() }
    }

    override fun localContentSource(localContentSource: () -> LocalContentSource) {
        diComponent.singleton(LocalContentSource::class) { localContentSource() }
    }

    override fun remoteContentSource(remoteContentSource: () -> RemoteContentSource) {
        diComponent.singleton(RemoteContentSource::class) { remoteContentSource() }
    }

    override fun exceptionHandler(exceptionHandler: () -> ExceptionHandler) {
        diComponent.singleton(ExceptionHandler::class) { exceptionHandler() }
    }

    override fun renderer(renderer: () -> Renderer<UiState>) {
        diComponent.singleton(Renderer::class) { renderer() }
    }

    override fun presenter(presenter: () -> Presenter<*, *>) {
        diComponent.singleton(Presenter::class) { presenter() }
    }

    override fun serializer(serializer: () -> KSerializer<*>) {
        diComponent.singleton(KSerializer::class) { serializer() }
    }

    override fun override(override: DiComponent.() -> Unit) {
        override(diComponent)
    }

    public fun build(): DiTree = DiTree(diComponent)
}