package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.core.contract.Contractor
import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.internal.di.DiComponent
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder
import org.alexcawl.sculptor.runtime.engine.SculptorBuilder
import org.alexcawl.sculptor.runtime.engine.dependencies.IntentResolver
import org.alexcawl.sculptor.runtime.engine.dependencies.SculptorLogger
import kotlin.reflect.KClass

internal class SculptorBuilderImpl(globalDiTree: DiTree) : SculptorBuilder, DiTreeBuilder {
    private val diComponent: DiComponent = globalDiTree.clone().diComponent

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

    override fun override(override: DiComponent.() -> Unit) {
        override(diComponent)
    }

    override fun build(): DiTree = DiTree(diComponent = diComponent)
}
