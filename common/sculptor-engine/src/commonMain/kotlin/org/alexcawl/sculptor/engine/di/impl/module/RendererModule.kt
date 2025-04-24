package org.alexcawl.sculptor.engine.di.impl.module

import org.alexcawl.sculptor.common.di.Module
import org.alexcawl.sculptor.common.di.factory
import org.alexcawl.sculptor.common.di.get
import org.alexcawl.sculptor.common.di.getAll
import org.alexcawl.sculptor.common.di.module
import org.alexcawl.sculptor.common.renderer.RendererScope
import org.alexcawl.sculptor.common.renderer.ResolveRenderer

internal fun rendererModule(): Module = module {
    factory{
        ResolveRenderer(renderers = getAll())
    }
    factory {
        RendererScope(resolveRenderer = get())
    }
}
