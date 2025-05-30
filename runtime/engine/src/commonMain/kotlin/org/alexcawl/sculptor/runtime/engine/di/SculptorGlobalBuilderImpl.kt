package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.core.contractor.Bundle
import org.alexcawl.sculptor.core.contractor.ContractBundle
import org.alexcawl.sculptor.core.contractor.contract
import org.alexcawl.sculptor.core.contractor.presenter.Presenter
import org.alexcawl.sculptor.core.contractor.renderer.Renderer
import org.alexcawl.sculptor.internal.di.DiComponent
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder
import org.alexcawl.sculptor.runtime.engine.SculptorGlobalBuilder
import org.alexcawl.sculptor.runtime.engine.dependencies.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.dependencies.IntentResolver
import org.alexcawl.sculptor.runtime.engine.dependencies.LocalContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.RemoteContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.SculptorLogger
import kotlin.reflect.KClass

internal class SculptorGlobalBuilderImpl : SculptorGlobalBuilder, DiTreeBuilder {
    private val diComponent: DiComponent = DiComponent()

    init {
        diComponent.addModules(
            storeModule(),
            reducersModule(),
            useCasesModule(),
            contractorModule(),
            presenterModule(),
            rendererModule(),
            defaultDependenciesModule(),
        )
    }

    override fun override(override: DiComponent.() -> Unit): Unit = override(diComponent)

    override fun contentResolutionStrategy(contentResolutionStrategy: () -> ContentResolutionStrategy) =
        diComponent.singleton(
            key = ContentResolutionStrategy::class,
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

    override fun sculptorLogger(sculptorLogger: () -> SculptorLogger) {
        diComponent.singleton(
            key = SculptorLogger::class,
            type = SculptorLogger::class,
            factory = { sculptorLogger() },
        )
    }

    override fun intentResolver(intentResolver: () -> IntentResolver) {
        diComponent.singleton(
            key = IntentResolver::class,
            type = IntentResolver::class,
            factory = { intentResolver() },
        )
    }

    override fun <K : Renderer<*>> renderer(key: KClass<K>, renderer: () -> K): Unit =
        diComponent.singleton(
            key = key,
            type = Renderer::class,
            factory = { renderer() },
        )

    override fun bundle(bundle: Bundle) {
        contract(bundle::contractBundle)
        bundle.presenterBundle.presenters.invoke(this)
        bundle.rendererBundle.renderers.invoke(this)
    }

    override fun <K : Presenter<*, *>> presenter(key: KClass<K>, presenter: () -> K): Unit =
        diComponent.singleton(
            key = key,
            type = Presenter::class,
            factory = { presenter() },
        )

    override fun <K : ContractBundle> contract(key: KClass<K>, contractor: () -> K) =
        diComponent.singleton(
            key = key,
            type = ContractBundle::class,
            factory = { contractor() },
        )

    override fun build(): DiTree = DiTree(diComponent = diComponent)
}
