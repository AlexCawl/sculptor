package org.alexcawl.sculptor.runtime.engine.di

import org.alexcawl.sculptor.core.contractor.renderer.RendererScope
import org.alexcawl.sculptor.internal.di.Module
import org.alexcawl.sculptor.internal.di.factory
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.di.getAll
import org.alexcawl.sculptor.internal.di.module
import org.alexcawl.sculptor.runtime.contractor.renderer.RendererProvider
import org.alexcawl.sculptor.runtime.contractor.renderer.RootRenderer
import org.alexcawl.sculptor.runtime.contractor.renderer.impl.RendererProviderImpl
import org.alexcawl.sculptor.runtime.contractor.renderer.impl.RendererScopeImpl
import org.alexcawl.sculptor.runtime.contractor.renderer.impl.RootRendererImpl

internal fun rendererModule(): Module = module {
    factory<RendererProvider> {
        RendererProviderImpl(renderers = getAll())
    }
    factory<RendererScope> {
        RendererScopeImpl(rendererProvider = get())
    }
    factory<RootRenderer> {
        RootRendererImpl(
            rendererProvider = get(),
            rendererScope = get(),
        )
    }
}