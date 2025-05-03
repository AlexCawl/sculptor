package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource

public sealed interface ContentResolutionStrategy {
    public data object RemoteFirst : ContentResolutionStrategy

    public data object LocalFirst : ContentResolutionStrategy
}
