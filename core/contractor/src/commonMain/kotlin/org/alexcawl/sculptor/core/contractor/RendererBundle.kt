package org.alexcawl.sculptor.core.contractor

import org.alexcawl.sculptor.core.contractor.renderer.Renderer
import kotlin.reflect.KClass

public interface RendererBundle {
    public val renderers: Consumer.() -> Unit

    public interface Consumer {
        public fun <K : Renderer<*>> renderer(key: KClass<K>, renderer: () -> K)
    }
}

public inline fun <reified K : Renderer<*>> RendererBundle.Consumer.renderer(noinline renderer: () -> K) {
    renderer(key = K::class, renderer = renderer)
}