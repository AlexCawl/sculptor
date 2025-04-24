package org.alexcawl.sculptor.engine.di.api

import androidx.lifecycle.LifecycleObserver

public interface SculptorBuilder {
    public fun lifecycleObserver(lifecycleObserver: () -> LifecycleObserver)
}
