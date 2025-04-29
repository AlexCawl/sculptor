package org.alexcawl.sculptor.engine.impl

import androidx.lifecycle.LifecycleObserver
import org.alexcawl.sculptor.common.contract.Contractor
import org.alexcawl.sculptor.internal.di.DiComponent
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.engine.SculptorBuilder
import kotlin.reflect.KClass

internal class SculptorBuilderImpl(globalDiTree: DiTree) : SculptorBuilder, DiTreeBuilder {
    private val diComponent: DiComponent = globalDiTree.diComponent.clone()

    override fun lifecycleObserver(lifecycleObserver: () -> LifecycleObserver) {
        diComponent.singleton(
            key = LifecycleObserver::class,
            type = LifecycleObserver::class,
            factory = { lifecycleObserver() },
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
