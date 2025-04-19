package org.alexcawl.sculptor.engine.api.contentService

public interface ContentResolutionStrategy {
    public data object OnlyRemote : ContentResolutionStrategy

    public data object RemoteMain : ContentResolutionStrategy

    public data object LocalMain : ContentResolutionStrategy
}
