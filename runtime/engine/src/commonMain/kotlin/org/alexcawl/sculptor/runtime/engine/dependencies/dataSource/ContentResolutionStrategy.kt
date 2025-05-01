package org.alexcawl.sculptor.runtime.engine.dependencies.dataSource

public interface ContentResolutionStrategy {
    public data object OnlyRemote : ContentResolutionStrategy

    public data object RemoteMain : ContentResolutionStrategy

    public data object LocalMain : ContentResolutionStrategy
}
