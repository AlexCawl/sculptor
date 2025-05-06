package org.alexcawl.sculptor.runtime.engine

import org.alexcawl.sculptor.core.contract.Contractor
import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.runtime.engine.dependencies.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.dependencies.LocalContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.RemoteContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.IntentResolver
import org.alexcawl.sculptor.runtime.engine.dependencies.SculptorLogger
import kotlin.reflect.KClass

public interface SculptorGlobalBuilder {
    public fun contentResolutionStrategy(contentResolutionStrategy: () -> ContentResolutionStrategy)

    public fun localContentSource(localContentSource: () -> LocalContentSource)

    public fun remoteContentSource(remoteContentSource: () -> RemoteContentSource)

    public fun sculptorLogger(sculptorLogger: () -> SculptorLogger)

    public fun intentResolver(intentResolver: () -> IntentResolver)

    public fun <K : Renderer<*>> renderer(key: KClass<K>, renderer: () -> K)

    public fun <K : Presenter<*, *>> presenter(key: KClass<K>, presenter: () -> K)

    public fun <K : Contractor> contractor(key: KClass<K>, contractor: () -> K)
}

public inline fun <reified K : Renderer<*>> SculptorGlobalBuilder.renderer(noinline renderer: () -> K) {
    renderer(K::class, renderer)
}

public inline fun <reified K : Presenter<*, *>> SculptorGlobalBuilder.presenter(noinline presenter: () -> K) {
    presenter(K::class, presenter)
}

public inline fun <reified K : Contractor> SculptorGlobalBuilder.contractor(noinline contractor: () -> K) {
    contractor(K::class, contractor)
}
