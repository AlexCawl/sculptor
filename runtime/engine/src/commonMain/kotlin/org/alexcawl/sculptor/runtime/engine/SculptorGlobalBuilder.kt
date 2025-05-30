package org.alexcawl.sculptor.runtime.engine

import org.alexcawl.sculptor.core.contractor.Bundle
import org.alexcawl.sculptor.core.contractor.ContractBundle
import org.alexcawl.sculptor.core.contractor.PresenterBundle
import org.alexcawl.sculptor.core.contractor.RendererBundle
import org.alexcawl.sculptor.core.contractor.presenter.Presenter
import org.alexcawl.sculptor.core.contractor.renderer.Renderer
import org.alexcawl.sculptor.runtime.engine.dependencies.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.dependencies.IntentResolver
import org.alexcawl.sculptor.runtime.engine.dependencies.LocalContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.RemoteContentSource
import org.alexcawl.sculptor.runtime.engine.dependencies.SculptorLogger
import kotlin.reflect.KClass

public interface SculptorGlobalBuilder : Bundle.Consumer, ContractBundle.Consumer, PresenterBundle.Consumer, RendererBundle.Consumer {
    public fun contentResolutionStrategy(contentResolutionStrategy: () -> ContentResolutionStrategy)

    public fun localContentSource(localContentSource: () -> LocalContentSource)

    public fun remoteContentSource(remoteContentSource: () -> RemoteContentSource)

    public fun sculptorLogger(sculptorLogger: () -> SculptorLogger)

    public fun intentResolver(intentResolver: () -> IntentResolver)

    public override fun <K : ContractBundle> contract(key: KClass<K>, contractor: () -> K)

    public override fun <K : Presenter<*, *>> presenter(key: KClass<K>, presenter: () -> K)

    public override fun <K : Renderer<*>> renderer(key: KClass<K>, renderer: () -> K)
}
