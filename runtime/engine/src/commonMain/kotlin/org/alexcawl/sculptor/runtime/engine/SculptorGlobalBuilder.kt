package org.alexcawl.sculptor.runtime.engine

import kotlinx.coroutines.CoroutineExceptionHandler
import org.alexcawl.sculptor.core.contract.Contractor
import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.LocalContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.RemoteContentSource
import kotlin.reflect.KClass

public interface SculptorGlobalBuilder {
    public fun <K : ContentResolutionStrategy> contentResolutionStrategy(key: KClass<K>, contentResolutionStrategy: () -> K)

    public fun localContentSource(localContentSource: () -> LocalContentSource)

    public fun remoteContentSource(remoteContentSource: () -> RemoteContentSource)

    public fun exceptionHandler(exceptionHandler: () -> CoroutineExceptionHandler)

    public fun <K : Renderer<*>> renderer(key: KClass<K>, renderer: () -> K)

    public fun <K : Presenter<*, *>> presenter(key: KClass<K>, presenter: () -> K)

    public fun <K : Contractor> contractor(key: KClass<K>, contractor: () -> K)
}

public inline fun <reified K : ContentResolutionStrategy> SculptorGlobalBuilder.contentResolutionStrategy(noinline contentResolutionStrategy: () -> K) {
    contentResolutionStrategy(K::class, contentResolutionStrategy)
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
