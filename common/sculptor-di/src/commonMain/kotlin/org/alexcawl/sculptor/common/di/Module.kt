package org.alexcawl.sculptor.common.di

public interface Module {
    public fun DiComponent.materialize()
}

public fun module(factory: Dependencies.() -> Unit): Module = DiModuleImpl(factory)

private class DiModuleImpl(private val factory: Dependencies.() -> Unit) : Module {
    override fun DiComponent.materialize() {
        return factory(this)
    }
}
