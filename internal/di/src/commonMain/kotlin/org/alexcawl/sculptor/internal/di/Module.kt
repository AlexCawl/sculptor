package org.alexcawl.sculptor.internal.di

import org.alexcawl.sculptor.internal.di.impl.DiModuleImpl

public interface Module {
    public fun DiComponent.install()
}

public fun module(factory: Dependencies.() -> Unit): Module = DiModuleImpl(factory)
