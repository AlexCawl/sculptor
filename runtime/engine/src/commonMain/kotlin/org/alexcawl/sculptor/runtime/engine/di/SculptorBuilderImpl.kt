package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.internal.di.DiComponent
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder
import org.alexcawl.sculptor.runtime.engine.SculptorBuilder
import org.alexcawl.sculptor.runtime.engine.dependencies.IntentResolver
import org.alexcawl.sculptor.runtime.engine.dependencies.SculptorLogger

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

    override fun override(override: DiComponent.() -> Unit) {
        override(diComponent)
    }

    override fun build(): DiTree = DiTree(diComponent = diComponent)
}
