package org.alexcawl.sculptor.engine.di.api

import org.alexcawl.sculptor.common.contract.Contractor
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.engine.api.ExceptionHandler
import org.alexcawl.sculptor.engine.api.contentService.ContentResolutionStrategy
import org.alexcawl.sculptor.engine.api.contentService.ContentResolver
import org.alexcawl.sculptor.engine.api.contentService.LocalContentSource
import org.alexcawl.sculptor.engine.api.contentService.RemoteContentSource
import kotlin.reflect.KClass

public interface SculptorGlobalBuilder {
    public fun <K : ContentResolver> contentResolver(key: KClass<K>, contentResolverProvider: () -> K)

    public fun <K : ContentResolutionStrategy> contentResolutionStrategy(key: KClass<K>, contentResolutionStrategy: () -> K)

    public fun localContentSource(localContentSource: () -> LocalContentSource)

    public fun remoteContentSource(remoteContentSource: () -> RemoteContentSource)

    public fun exceptionHandler(exceptionHandler: () -> ExceptionHandler)

    public fun <K : Renderer<*>> renderer(key: KClass<K>, renderer: () -> K)

    public fun <K : Presenter<*, *>> presenter(key: KClass<K>, presenter: () -> K)

    public fun <K : Contractor> contractor(key: KClass<K>, contractor: () -> K)
}

public inline fun <reified K : ContentResolver> SculptorGlobalBuilder.contentResolver(noinline contentResolverProvider: () -> K) {
    contentResolver(K::class, contentResolverProvider)
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
